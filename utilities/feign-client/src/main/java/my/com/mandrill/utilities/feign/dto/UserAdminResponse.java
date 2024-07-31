package my.com.mandrill.utilities.feign.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.com.mandrill.utilities.general.constant.LoginTypeEnum;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserAdminResponse {

	private String id;

	private String username;

	private Set<AuthorityDTO> authorities;

	private List<InstitutionDTO> institutions;

	private String email;

	private LoginTypeEnum loginType;

	private String fullName;

}
