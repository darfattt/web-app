package my.com.mandrill.utilities.general.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import my.com.mandrill.utilities.general.dto.model.UserLoginDetailDTO;
import my.com.mandrill.utilities.general.exception.ExceptionPredicate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityUtil {

	private static Optional<String> getCurrentUsername() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return Optional.ofNullable(securityContext.getAuthentication()).map(authentication -> {
			Object object = authentication.getPrincipal();
			if (object instanceof UserLoginDetailDTO) {
				UserLoginDetailDTO userDetails = (UserLoginDetailDTO) object;
				return userDetails.getUsername();
			}
			else if (object instanceof UserDetails userDetails) {
				String usernameDetails = userDetails.getUsername();
				try {
					UserLoginDetailDTO userLoginDetailDTO = ObjectMapperUtil.MAPPER.readValue(usernameDetails,
							UserLoginDetailDTO.class);
					return userLoginDetailDTO.getUsername();
				}
				catch (JsonParseException e) {
				}
				catch (JsonMappingException e) {
				}
				catch (JsonProcessingException e) {
				}
				return usernameDetails;
			}
			else if (object instanceof String string) {
				return string;
			}
			return null;
		});
	}

	public static boolean isAnonymous() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication instanceof AnonymousAuthenticationToken;
	}

	public static String currentUserLogin() {
		return getCurrentUsername().orElseThrow(ExceptionPredicate.cantAccessUser());
	}

	public static Optional<String> getCurrentUserRefNo() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return Optional.ofNullable(securityContext.getAuthentication())
				.map(authentication -> (String) authentication.getDetails());
	}

	public static boolean isCurrentUserInRole(String authority) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return Optional
				.ofNullable(securityContext.getAuthentication()).map(authentication -> authentication.getAuthorities()
						.stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority)))
				.orElse(false);
	}

	public static Optional<UserLoginDetailDTO> getCurrentUserLoginDetail() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return Optional.ofNullable(securityContext.getAuthentication()).map(authentication -> {
			Object object = authentication.getPrincipal();
			if (object instanceof UserLoginDetailDTO) {
				UserLoginDetailDTO userDetails = (UserLoginDetailDTO) object;
				return userDetails;
			}
			else if (object instanceof UserDetails userDetails) {
				String usernameDetails = userDetails.getUsername();
				try {
					UserLoginDetailDTO userLoginDetailDTO = ObjectMapperUtil.MAPPER.readValue(usernameDetails,
							UserLoginDetailDTO.class);
					return userLoginDetailDTO;
				}
				catch (JsonParseException e) {
				}
				catch (JsonMappingException e) {
				}
				catch (JsonProcessingException e) {
				}
				return null;
			}

			return null;
		});
	}

}
