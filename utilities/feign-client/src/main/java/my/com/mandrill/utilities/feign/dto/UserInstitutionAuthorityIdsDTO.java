package my.com.mandrill.utilities.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInstitutionAuthorityIdsDTO implements Serializable {

	private String userId;

	private String institutionId;

	private String authorityId;

	private String paraSportsId;

}
