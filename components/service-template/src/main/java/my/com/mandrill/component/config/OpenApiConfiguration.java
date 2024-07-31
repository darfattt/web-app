package my.com.mandrill.component.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@OpenAPIDefinition(info = @Info(title = "Service Template Component API"),
		security = { @SecurityRequirement(name = "bearerAuth") })
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer",
		in = SecuritySchemeIn.HEADER)
@Configuration
public class OpenApiConfiguration {

	private final BaseProperties baseProperties;

	public OpenApiConfiguration(BaseProperties baseProperties) {
		this.baseProperties = baseProperties;
	}

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().servers(List.of(new Server().url(baseProperties.getRequestUri().getOpenApiServer())));
	}

}
