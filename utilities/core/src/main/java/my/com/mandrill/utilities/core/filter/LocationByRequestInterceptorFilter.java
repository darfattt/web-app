package my.com.mandrill.utilities.core.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.core.callback.UserActivityCallback;
import my.com.mandrill.utilities.core.filter.wrapper.CachedHttpServletRequestWrapper;
import my.com.mandrill.utilities.general.constant.UserActivityTypeEnum;
import my.com.mandrill.utilities.general.util.InstitutionUtil;
import my.com.mandrill.utilities.general.util.ObjectMapperUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
public class LocationByRequestInterceptorFilter extends OncePerRequestFilter {

	private final UserActivityCallback userActivityCallback;

	private final ObjectMapper objectMapper;

	public LocationByRequestInterceptorFilter(UserActivityCallback userActivityCallback) throws IOException {
		this.userActivityCallback = userActivityCallback;
		this.objectMapper = ObjectMapperUtil.MAPPER;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		filterChain.doFilter(request, response);
		storeActivity(new CachedHttpServletRequestWrapper(request), response);
	}

	private void storeActivity(HttpServletRequest request, HttpServletResponse response) {
		String institutionId = this.getInstitutionId(request);
		String ipAddress = request.getRemoteAddr();
		String userAgentString = request.getHeader(HttpHeaders.USER_AGENT);
		UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
		String requestURI = request.getRequestURI();
		UserActivityTypeEnum activity = UserActivityTypeEnum.fromRequestUrls(requestURI);
		String remarks = this.constructRemark(requestURI, request);
		String status = HttpStatus.valueOf(response.getStatus()).getReasonPhrase();
		this.saveUserActivity(institutionId, activity.toString(), ipAddress, userAgent.toString(), remarks, status);
	}

	private void saveUserActivity(String institutionId, String activity, String ipAddress, String deviceName,
			String remarks, String status) {
		userActivityCallback.saveUserActivity(institutionId, activity, ipAddress, deviceName, remarks, status);
	}

	private String getInstitutionId(HttpServletRequest request) {
		String institutionId = request.getParameter("institutionId");
		log.debug("Institution ID from request parameter = [{}]", institutionId);
		// get from header
		if (institutionId == null) {
			institutionId = InstitutionUtil.getInstitutionHeader(request);
			log.debug("Institution ID from request Headers = [{}]", institutionId);
		}
		return institutionId;
	}

	private String constructRemark(String requestURI, HttpServletRequest request) {
		StringBuilder remarksSb = new StringBuilder();
		remarksSb.append(requestURI);
		// Add request body content
		try {
			String requestBody = getRequestBody(request);
			if (!requestBody.isEmpty()) {
				JsonNode jsonNode = this.objectMapper.readTree(requestBody);
				if (jsonNode.has("name")) {
					remarksSb.append(" : \n");
					remarksSb.append("name: ").append(jsonNode.get("name").asText()).append("\n");
				}
			}
		}
		catch (IOException e) {
			log.warn("Error reading request body to find field name");
		}
		return remarksSb.toString();
	}

	private String getRequestBody(HttpServletRequest request) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		try (BufferedReader bufferedReader = request.getReader()) {
			stringBuilder.append(bufferedReader.lines().collect(Collectors.joining("\n")));
		}
		return stringBuilder.toString();
	}

}
