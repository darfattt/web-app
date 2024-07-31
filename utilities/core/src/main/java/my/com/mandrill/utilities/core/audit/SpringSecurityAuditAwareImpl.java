package my.com.mandrill.utilities.core.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.general.constant.Constant;
import my.com.mandrill.utilities.general.dto.model.UserLoginDetailDTO;
import my.com.mandrill.utilities.general.util.ObjectMapperUtil;

@Slf4j
public class SpringSecurityAuditAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return Optional.of(Constant.ANONYMOUS_USER);
		}
		else if (!authentication.isAuthenticated()) {
			return Optional.of(Constant.SYSTEM_ACCOUNT);
		}

		if (authentication.getPrincipal() instanceof UserLoginDetailDTO) {
			UserLoginDetailDTO principle = (UserLoginDetailDTO) authentication.getPrincipal();
			return Optional.ofNullable(principle.getUsername());
		}
		else if (authentication.getPrincipal() instanceof UserDetails) {
			UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
			String usernameDetails = springSecurityUser.getUsername();
			try {
				UserLoginDetailDTO userLoginDetailDTO = ObjectMapperUtil.MAPPER.readValue(usernameDetails,
						UserLoginDetailDTO.class);
				return Optional.ofNullable(userLoginDetailDTO.getUsername());
			}
			catch (JsonParseException e) {
			}
			catch (JsonMappingException e) {
			}
			catch (JsonProcessingException e) {
			}
			return Optional.ofNullable(usernameDetails);
		}
		else if (authentication.getPrincipal() instanceof String) {
			String userPrincipal = (String) authentication.getPrincipal();
			return Optional.ofNullable(userPrincipal);
		}

		return null;
	}

}