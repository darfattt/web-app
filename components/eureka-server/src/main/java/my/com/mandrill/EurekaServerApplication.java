package my.com.mandrill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.TimeZone;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	public static void main(String[] args) {
		// TimeConstant is coming form general, importing them will include their
		// dependencies which is unnecessary
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
