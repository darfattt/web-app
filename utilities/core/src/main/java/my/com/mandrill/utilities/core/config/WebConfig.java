package my.com.mandrill.utilities.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import my.com.mandrill.utilities.core.interceptor.InstitutionInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final InstitutionInterceptor institutionInterceptor;

	public WebConfig(InstitutionInterceptor institutionInterceptor) {
		this.institutionInterceptor = institutionInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(institutionInterceptor);
	}

}
