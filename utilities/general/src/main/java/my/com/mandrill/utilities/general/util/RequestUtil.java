package my.com.mandrill.utilities.general.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestUtil {

	public static final String API_KEY = "api-key";

	public static final String SIGNATURE_CHALLENGE = "signature-challenge";

	public static final String CREDENTIAL = "credential";

	private static final List<String> IP_HEADERS = List.of("x-real-ip", "x-forwarded-for");

	private static final List<String> HOST_HEADERS = List.of(HttpHeaders.ORIGIN, "x-forwarded-host",
			"x-forwarded-server");

	public static String getIpAddress(HttpServletRequest request) {
		for (String header : IP_HEADERS) {
			if (StringUtils.isNotBlank(request.getHeader(header))) {
				return request.getHeader(header);
			}
		}
		return request.getRemoteAddr();
	}

	public static String getHost(HttpServletRequest request) {
		for (String header : HOST_HEADERS) {
			if (StringUtils.isNotBlank(request.getHeader(header))) {
				return request.getHeader(header);
			}
		}
		return request.getRemoteHost();
	}

}
