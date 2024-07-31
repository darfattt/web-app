package my.com.mandrill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		// TimeConstant is coming form general, importing them will include their
		// dependencies which is unnecessary
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
