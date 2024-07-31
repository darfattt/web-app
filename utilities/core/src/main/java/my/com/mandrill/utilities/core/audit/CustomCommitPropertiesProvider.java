package my.com.mandrill.utilities.core.audit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.core.constant.JvCommitPropertiesEnum;
import my.com.mandrill.utilities.core.context.InstitutionContext;
import my.com.mandrill.utilities.feign.client.AccountFeignClient;
import my.com.mandrill.utilities.feign.dto.UserDTO;
import my.com.mandrill.utilities.general.util.SecurityUtil;
import org.javers.spring.auditable.CommitPropertiesProvider;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
@AllArgsConstructor
public class CustomCommitPropertiesProvider implements CommitPropertiesProvider {

	private final AccountFeignClient accountFeignClient;

	@Override
	public Map<String, String> provide() {
		Map<String, String> commitProperties = new HashMap<>();
		UserDTO userLoginDTO = getUserLogin();
		if (userLoginDTO != null) {
			commitProperties.put(JvCommitPropertiesEnum.USER_FULL_NAME.getKey(), userLoginDTO.getFullName());
			commitProperties.put(JvCommitPropertiesEnum.USER_EMAIL.getKey(), userLoginDTO.getEmail());
		}
		String institutionId = getInstitutionIdFromContext();
		commitProperties.put(JvCommitPropertiesEnum.INSTITUTION_ID.getKey(), institutionId);
		return commitProperties;
	}

	private UserDTO getUserLogin() {
		try {
			if (Objects.isNull(SecurityUtil.currentUserLogin()))
				return null;
			return accountFeignClient.getAccount();
		}
		catch (Exception e) {
			return null;
		}
	}

	private String getInstitutionIdFromContext() {
		return InstitutionContext.getInstitutionId();
	}

}
