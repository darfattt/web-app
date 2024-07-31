package my.com.mandrill.utilities.ciphers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "securities", ignoreUnknownFields = true)
public class SecuritiesProperties {

	private Crypto crypto;

	@Getter
	@Setter
	public static class Crypto {

		private String key;

		private String iv;

	}

}
