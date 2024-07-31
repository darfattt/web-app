package my.com.mandrill.utilities.storage.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class FileStoragePropertiesConfiguration {

	@Bean
	@Primary
	@Qualifier("publicStorageProperty")
	@ConfigurationProperties(prefix = "file-storage.public", ignoreUnknownFields = true)
	public FileStorageProperties publicStorageProperty() {
		return new FileStorageProperties();
	}

	@Bean
	@Qualifier("privateStorageProperty")
	@ConfigurationProperties(prefix = "file-storage.private", ignoreUnknownFields = true)
	public FileStorageProperties privateStorageProperty() {
		return new FileStorageProperties();
	}

}
