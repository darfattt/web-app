package my.com.mandrill.utilities.core.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.core.context.InstitutionContext;
import my.com.mandrill.utilities.general.util.InstitutionUtil;

@Component
@Slf4j
public class InstitutionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String institutionId = InstitutionUtil.getInstitutionHeader(request);
		if (institutionId != null) {
			InstitutionContext.setInstitutionId(institutionId);
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		InstitutionContext.clear(); // Clear the context to prevent memory leaks
	}

}