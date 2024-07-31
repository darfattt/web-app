package my.com.mandrill.utilities.core.audit;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Slf4j
@Configuration
@EnableJpaAuditing
public class AuditingConfiguration {

	@Bean
	public AuditorAware<String> auditorProvider() {
		return new SpringSecurityAuditAwareImpl();
	}

}
