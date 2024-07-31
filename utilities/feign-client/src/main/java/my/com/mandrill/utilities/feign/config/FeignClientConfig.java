package my.com.mandrill.utilities.feign.config;

import feign.codec.ErrorDecoder;
import my.com.mandrill.utilities.feign.exception.ApiErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

	@Bean
	public ErrorDecoder errorDecoder() {
		return new ApiErrorDecoder();
	}

}
