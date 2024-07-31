package my.com.mandrill.container;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

public class PostgresAndKafkaContainerInit implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));

	static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

	static {
		Startables.deepStart(postgres, kafka).join();
	}

	@Override
	public void initialize(ConfigurableApplicationContext ctx) {
		TestPropertyValues.of("spring.datasource.url=" + postgres.getJdbcUrl(),
				"spring.datasource.username=" + postgres.getUsername(),
				"spring.datasource.password=" + postgres.getPassword(),
				"spring.kafka.bootstrap-servers=" + kafka.getBootstrapServers()).applyTo(ctx.getEnvironment());

	}

}
