package my.com.mandrill.utilities.core.security;

import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.feign.client.AccountFeignClient;
import my.com.mandrill.utilities.general.dto.model.UserLoginDetailDTO;
import my.com.mandrill.utilities.general.exception.BusinessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

	public static final String PHONE_NOT_VERIFIED = "PHONE_NOT_VERIFIED";

	public static final String INVALID_CREDENTIAL = "INVALID_CREDENTIAL";

	public static final String DELETED_ACCOUNT = "DELETED_ACCOUNT";

	public static final String LOGIN_ATTEMPT_EXCEED = "LOGIN_ATTEMPT_EXCEED";

	public static final String USER_NOT_FOUND = "USER_NOT_FOUND";

	private static final String DELETED_ACCOUNT_ERROR_CODE = "31030";

	private static final String LOGIN_ATTEMPT_EXCEED_ERROR_CODE = "31011";

	private static final String USER_NOT_FOUND_ERROR_CODE = "31044";

	private final AccountFeignClient accountFeignClient;

	public UsernamePasswordAuthenticationProvider(AccountFeignClient accountFeignClient) {
		this.accountFeignClient = accountFeignClient;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		LoginDetails loginDetails = (LoginDetails) authentication.getDetails();
		String username = authentication.getName();
		String password = String.valueOf(authentication.getCredentials());
		String accessType = loginDetails.getAccessType().toString();
		String loginType = loginDetails.getLoginType().toString();
		String institutionName = loginDetails.getInstitutionName();

		List<String> permissionCodeList;
		List<String> institutionIds = new ArrayList<>();
		try {
			log.info("accountFeignClient.authenticateUser");
			permissionCodeList = accountFeignClient.authenticateUser(accessType, loginType, username, password,
					institutionName);

			if (permissionCodeList == null || permissionCodeList.isEmpty()) {
				permissionCodeList = new ArrayList<>();
				username = PHONE_NOT_VERIFIED;
			}
			else {
				log.info("accountFeignClient.getRefNoByUsernameAndLoginTypeAndAccessType");
				username = accountFeignClient.getRefNoByUsernameAndLoginTypeAndAccessType(accessType, loginType,
						username, institutionName);

			}
		}
		catch (BusinessException e) {
			permissionCodeList = new ArrayList<>();
			username = INVALID_CREDENTIAL;
			if (e.getApiError() != null) {
				if (DELETED_ACCOUNT_ERROR_CODE.equals(e.getApiError().getErrorCode())) {
					username = DELETED_ACCOUNT;
				}
				else if (LOGIN_ATTEMPT_EXCEED_ERROR_CODE.equals(e.getApiError().getErrorCode())) {
					username = LOGIN_ATTEMPT_EXCEED;
				}
				else if (USER_NOT_FOUND_ERROR_CODE.equals(e.getApiError().getErrorCode())) {
					username = USER_NOT_FOUND;
				}
			}
		}
		catch (Exception e) {
			log.info("UsernamePasswordAuthenticationProvider.authenticate()");
			e.printStackTrace();
			permissionCodeList = new ArrayList<>();
			username = INVALID_CREDENTIAL;
		}
		UserLoginDetailDTO principal = new UserLoginDetailDTO(username, institutionIds);
		List<SimpleGrantedAuthority> grantedAuthorities = permissionCodeList.stream().map(SimpleGrantedAuthority::new)
				.toList();
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal, password,
				grantedAuthorities);
		token.setDetails(new LoginDetails(loginDetails.getAccessType(), loginDetails.getLoginType(), institutionName));
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}
