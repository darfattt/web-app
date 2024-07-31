package my.com.mandrill.component.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfiguration {

	@Bean
	@Lazy(false)
	public List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfigParameters,
			RouteDefinitionLocator locator) {

		List<GroupedOpenApi> groups = new ArrayList<>();
		List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
		assert definitions != null;
		definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-component"))
				.forEach(routeDefinition -> {
					String name = routeDefinition.getId();
					swaggerUiConfigParameters.addGroup(name);
					groups.add(GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build());
				});
		return groups;
	}

}
