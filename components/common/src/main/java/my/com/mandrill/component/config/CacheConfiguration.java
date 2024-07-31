package my.com.mandrill.component.config;

import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.general.exception.GeneralCacheErrorHandler;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableCaching
public class CacheConfiguration implements CachingConfigurer {

	@Override
	public CacheErrorHandler errorHandler() {
		return new GeneralCacheErrorHandler();
	}

}
