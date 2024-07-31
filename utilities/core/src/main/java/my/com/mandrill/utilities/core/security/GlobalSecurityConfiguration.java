package my.com.mandrill.utilities.core.security;

import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.core.security.jwt.JWTConfigurer;
import my.com.mandrill.utilities.core.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;
import java.util.Objects;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalSecurityConfiguration {

	@Autowired
	private TokenProvider tokenProvider;

	@Autowired(required = false)
	private Customizer<OAuth2LoginConfigurer<HttpSecurity>> oAuth2Customizer;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(authenticationProvider);
		return authenticationManagerBuilder.build();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and()
				.headers().frameOptions().disable().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable().cors()
				.configurationSource(request -> {
					CorsConfiguration configuration = new CorsConfiguration();
					configuration.setAllowedOrigins(List.of("*"));
					configuration.setAllowedMethods(List.of("*"));
					configuration.setAllowedHeaders(List.of("*"));
					configuration.setExposedHeaders(List.of("*"));
					return configuration;
				}).and()
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/swagger-ui/**").permitAll()
						.requestMatchers("/swagger-resources/**").permitAll().requestMatchers("/v3/api-docs/**")
						.permitAll().requestMatchers("/actuator/**").permitAll()

						.requestMatchers("/sms").permitAll()

						.requestMatchers("/oauth2/**").permitAll().requestMatchers("/login/oauth2/**").permitAll()
						.requestMatchers("/authenticate").permitAll()
						.requestMatchers(HttpMethod.POST, "/authenticate/signature-challenge").permitAll()
						.requestMatchers("/account/register").permitAll()
						.requestMatchers(HttpMethod.GET, "/account/ref-no").permitAll()
						.requestMatchers(HttpMethod.GET, "/account/institutions-authority").permitAll()
						.requestMatchers("/account/reset-password-email").permitAll()
						.requestMatchers("/account/reset-password-mobile").permitAll()
						.requestMatchers(HttpMethod.PUT, "/admin/user/reset-password").permitAll()
						.requestMatchers(HttpMethod.GET, "/admin/user/reset-password").permitAll()
						.requestMatchers(HttpMethod.GET, "/account/request-otp").permitAll()
						.requestMatchers(HttpMethod.GET, "/account/verify-otp").permitAll()
						.requestMatchers("/account/validate-phone").permitAll()
						.requestMatchers(HttpMethod.GET, "/brand/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/series/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/model/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/enquiry").permitAll()
						.requestMatchers(HttpMethod.GET, "/bid-product/history/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/trade-product/search").permitAll()
						.requestMatchers(HttpMethod.GET, "/trade-product/detail/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/system-config/public/**").permitAll()
						.requestMatchers("/socket").permitAll().requestMatchers("/topic/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/transactions/callback").permitAll()
						.requestMatchers(HttpMethod.POST, "/transactions/response").permitAll()
						.requestMatchers("/upload-transactions/**").permitAll()
						.requestMatchers("/user-advertisements/registration").permitAll()
						.requestMatchers("/emails/contact-us").permitAll().requestMatchers("/emails").permitAll()
						.requestMatchers("/email-blacklist").permitAll().requestMatchers("/registration-key/validation")
						.permitAll().requestMatchers("/registration-key/confirm-register").permitAll()
						.requestMatchers("/staging-institution/main-registration").permitAll()
						.requestMatchers("/staging-institution/site-registration").permitAll()
						.requestMatchers("/staging-institution/entity-registration").permitAll()
						.requestMatchers("/staging-user/user-registration").permitAll().requestMatchers("/lookup")
						.permitAll().requestMatchers(HttpMethod.GET, "/country").permitAll()
						.requestMatchers(HttpMethod.GET, "/state").permitAll()
						.requestMatchers("/institution/site-entity-validation").permitAll()
						.requestMatchers("/disclosure-request/approve-registration-consent").permitAll()
						.requestMatchers("/admin/institution/confirm-register").permitAll()
						.requestMatchers("/account/first-login/validation").permitAll()
						.requestMatchers("/account/first-login/set-password").permitAll()
						.requestMatchers("/admin/user/validation").permitAll()
						.requestMatchers("/admin/audit/searchAudit").permitAll()
						.requestMatchers("/admin/user/validation").permitAll().requestMatchers("/para-sports/all")
						.permitAll().requestMatchers("/institution/sports-association-validation").permitAll()
						.requestMatchers("/institution/sports-association-registration").permitAll()
						.requestMatchers("/admin/user/user-validation").permitAll()
						.requestMatchers("/admin/institution/activate-institution").permitAll()
						.requestMatchers("/admin/user/reset-password/staff-id-validation").permitAll()
						.requestMatchers("/admin/user/reset-password/verification-question").permitAll()

						.anyRequest().authenticated())
				.httpBasic().disable().apply(securityConfigurerAdapter());

		if (Objects.nonNull(oAuth2Customizer)) {
			http.oauth2Login(oAuth2Customizer);
		}

		return http.build();
	}

	private JWTConfigurer securityConfigurerAdapter() {
		return new JWTConfigurer(tokenProvider);
	}

}
