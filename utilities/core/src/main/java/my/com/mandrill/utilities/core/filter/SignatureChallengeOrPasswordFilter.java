package my.com.mandrill.utilities.core.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.feign.client.AccountFeignClient;
import my.com.mandrill.utilities.feign.dto.model.DeviceKeyDTO;
import my.com.mandrill.utilities.feign.dto.model.SignatureChallengeDTO;
import my.com.mandrill.utilities.general.util.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SignatureChallengeOrPasswordFilter extends OncePerRequestFilter {

	private final AccountFeignClient accountFeignClient;

	private final AntPathMatcher antPathMatcher = new AntPathMatcher();

	private final HandlerExceptionResolver handlerExceptionResolver;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) {

		try {
			String signatureChallenge = request.getHeader(RequestUtil.SIGNATURE_CHALLENGE);
			String credential = request.getHeader(RequestUtil.CREDENTIAL);

			if (StringUtils.isNotBlank(signatureChallenge)) {
				String[] item = signatureChallenge.split(";");
				if (item.length == 3) {
					SignatureChallengeDTO signatureChallengeDTO = new SignatureChallengeDTO();
					DeviceKeyDTO deviceKeyDTO = new DeviceKeyDTO();
					deviceKeyDTO.setDeviceId(item[0]);
					signatureChallengeDTO.setDeviceKey(deviceKeyDTO);
					signatureChallengeDTO.setId(item[1]);
					signatureChallengeDTO.setSignature(item[2]);
					accountFeignClient.assertionFinish(signatureChallengeDTO);
				}
				else {
					throw new BadCredentialsException("Failed to authenticate");
				}
			}
			else if (StringUtils.isNotBlank(credential)) {
				accountFeignClient.checkPassword(credential);
			}
			else {
				throw new BadCredentialsException("Failed to authenticate");
			}
			filterChain.doFilter(request, response);
		}
		catch (Exception e) {
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}

			log.error("Exception during Authentication: {}", e.getMessage());
			handlerExceptionResolver.resolveException(request, response, null, e);
		}
	}

	@Override
	protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
		List<Request> urlList = List.of(new Request("/vaults/download/*", HttpMethod.GET));
		return urlList.stream().noneMatch(s -> antPathMatcher.match(s.getPath(), request.getServletPath())
				&& request.getMethod().equals(s.getMethod().name()));
	}

	@Data
	@AllArgsConstructor
	private static class Request {

		private String path;

		private HttpMethod method;

	}

}
