package my.com.mandrill.utilities.general.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.lang.NonNull;

@Slf4j
public class GeneralCacheErrorHandler implements CacheErrorHandler {

	@Override
	public void handleCacheGetError(@NonNull RuntimeException exception, @NonNull Cache cache, @NonNull Object key) {
		handleDebug(exception);
		log.error("handleCacheGetError: {}, cache: {}, key: {}", exception.getMessage(), cache.getName(), key);
	}

	@Override
	public void handleCachePutError(@NonNull RuntimeException exception, @NonNull Cache cache, @NonNull Object key,
			Object value) {
		handleDebug(exception);
		log.error("handleCachePutError: {}, cache: {}, key: {}, value: {}", exception.getMessage(), cache.getName(),
				key, value);
	}

	@Override
	public void handleCacheEvictError(@NonNull RuntimeException exception, @NonNull Cache cache, @NonNull Object key) {
		handleDebug(exception);
		log.error("handleCacheEvictError: {}, cache: {}, key: {}", exception.getMessage(), cache.getName(), key);
	}

	@Override
	public void handleCacheClearError(@NonNull RuntimeException exception, @NonNull Cache cache) {
		handleDebug(exception);
		log.error("handleCacheClearError: {}, cache: {}", exception.getMessage(), cache.getName());
	}

	private void handleDebug(RuntimeException e) {
		if (log.isDebugEnabled()) {
			e.printStackTrace();
		}
	}

}
