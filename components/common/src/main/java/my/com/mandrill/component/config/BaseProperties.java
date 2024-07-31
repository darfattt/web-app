package my.com.mandrill.component.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "base")
public class BaseProperties {

	private final RequestUri requestUri = new RequestUri();

	private final CDN cdn = new CDN();

	private final Cache cache = new Cache();

	private String adminEmail;

	@Getter
	@Setter
	public static class RequestUri {

		private String openApiServer;

	}

	@Getter
	@Setter
	public static class CDN {

		private String host;

	}

	@Getter
	@Setter
	public static class Cache {

		private final Ehcache ehcache = new Ehcache();

		@Getter
		@Setter
		public static class Ehcache {

			private Long maxEntries;

			private Long timeToLiveSeconds;

		}

	}

}
