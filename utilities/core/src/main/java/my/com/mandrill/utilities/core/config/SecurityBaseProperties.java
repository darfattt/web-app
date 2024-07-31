package my.com.mandrill.utilities.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "security", ignoreUnknownFields = true)
public class SecurityBaseProperties {

	private final Jwt jwt = new Jwt();

	@Getter
	@Setter
	public static class Jwt {

		private String base64Secret;

		private long tokenValidityInSeconds;

		private long tokenValidityInSecondsForRememberMe;

		private boolean tokenExpiryEnabled;

	}

}
