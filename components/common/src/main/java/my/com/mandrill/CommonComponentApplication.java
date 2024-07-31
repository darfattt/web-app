package my.com.mandrill;

import my.com.mandrill.utilities.general.constant.TimeConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@ComponentScan("my.com.mandrill")
@EnableJpaRepositories(
		basePackages = { "my.com.mandrill.component.repository.jpa", "my.com.mandrill.utilities.general.repository" })
@EnableElasticsearchRepositories(basePackages = { "my.com.mandrill.component.repository.search" })
public class CommonComponentApplication
{

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone(TimeConstant.APPLICATION_TIMEZONE));
		SpringApplication.run(CommonComponentApplication.class, args);
	}

}
