package my.com.mandrill.utilities.general.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionUtil {

	// Hotfix because of an issue running in kubernetes :
	// https://github.com/reactor/reactor-netty/issues/1774
	// TODO: make it configurable
	public static ConnectionProvider getDefaultConnectionProvider() {
		return ConnectionProvider.builder("fixed").maxConnections(500).maxIdleTime(Duration.ofSeconds(20))
				.maxLifeTime(Duration.ofSeconds(60)).pendingAcquireTimeout(Duration.ofSeconds(60))
				.evictInBackground(Duration.ofSeconds(120)).build();
	}

}
