package my.com.mandrill.utilities.core.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.core.config.SecurityBaseProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

	private static final String AUTHORITIES_KEY = "auth";

	private static final String IDENTITY_REFERENCE = "ref";

	private static final String INVALID_TOKEN_ERROR_MESSAGE = "Invalid JWT token.";

	private final Key key;

	private final JwtParser jwtParser;

	private final long tokenValidityInMilliseconds;

	private final long tokenValidityInMillisecondsForRememberMe;

	private final SecurityBaseProperties securityBaseProperties;

	public TokenProvider(SecurityBaseProperties securityBaseProperties) {
		this.securityBaseProperties = securityBaseProperties;

		byte[] keyBytes;
		keyBytes = Decoders.BASE64.decode(this.securityBaseProperties.getJwt().getBase64Secret());
		key = Keys.hmacShaKeyFor(keyBytes);
		jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
		this.tokenValidityInMilliseconds = 1000 * this.securityBaseProperties.getJwt().getTokenValidityInSeconds();
		this.tokenValidityInMillisecondsForRememberMe = 1000
				* this.securityBaseProperties.getJwt().getTokenValidityInSecondsForRememberMe();
	}

	public String createToken(Authentication authentication, String refNo, boolean rememberMe) {
		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		return createDefaultToken(authorities, refNo, authentication.getName(), rememberMe);
	}

	public String createDefaultToken(String authorities, String refNo, String subject, boolean rememberMe) {
		long now = (new Date()).getTime();
		Date validity;
		if (rememberMe) {
			validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
		}
		else {
			validity = new Date(now + this.tokenValidityInMilliseconds);
		}

		JwtBuilder jwtBuilder = Jwts.builder().setSubject(subject).claim(IDENTITY_REFERENCE, refNo)
				.claim(AUTHORITIES_KEY, authorities).signWith(key, SignatureAlgorithm.HS512);

		if (securityBaseProperties.getJwt().isTokenExpiryEnabled()) {
			jwtBuilder.setExpiration(validity);
		}

		return jwtBuilder.compact();
	}

	public Authentication getAuthentication(String token) {
		Claims claims = jwtParser.parseClaimsJws(token).getBody();

		Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).filter(auth -> !auth.trim().isEmpty())
				.map(SimpleGrantedAuthority::new).toList();

		User principal = new User(claims.getSubject(), "", authorities);

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal, token,
				authorities);
		auth.setDetails(claims.get(IDENTITY_REFERENCE));
		return auth;
	}

	public boolean validateToken(String authToken) {
		try {
			Jws<Claims> claimsJws = jwtParser.parseClaimsJws(authToken);
			if (securityBaseProperties.getJwt().isTokenExpiryEnabled() && claimsJws.getBody().getExpiration() == null) {
				throw new ExpiredJwtException(null, claimsJws.getBody(), null);
			}
			return true;
		}
		catch (ExpiredJwtException e) {
			log.trace(INVALID_TOKEN_ERROR_MESSAGE, e);
		}
		catch (UnsupportedJwtException e) {
			log.trace(INVALID_TOKEN_ERROR_MESSAGE, e);
		}
		catch (MalformedJwtException e) {
			log.trace(INVALID_TOKEN_ERROR_MESSAGE, e);
		}
		catch (SecurityException e) {
			log.trace(INVALID_TOKEN_ERROR_MESSAGE, e);
		}
		catch (IllegalArgumentException e) {
			log.error("Token validation error {}", e.getMessage());
		}

		return false;
	}

}
