package my.com.mandrill.utilities.feign.inteceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.general.constant.HttpHeaderKeyEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Slf4j
@Component
public class AuthForwardInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		try {
			HttpServletRequest request = ((ServletRequestAttributes) Objects
					.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
			template.header(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
			template.header(HttpHeaderKeyEnum.INSTITUTION.getKey(),
					request.getHeader(HttpHeaderKeyEnum.INSTITUTION.getKey()));
			template.header(HttpHeaderKeyEnum.SUB_INSTITUTION.getKey(),
					request.getHeader(HttpHeaderKeyEnum.SUB_INSTITUTION.getKey()));
			template.header(HttpHeaders.USER_AGENT, request.getHeader(HttpHeaders.USER_AGENT));
		}
		catch (NullPointerException e) {
			log.warn("Accessing Feign Client without request context");
		}
	}

}
