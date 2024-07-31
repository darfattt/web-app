package my.com.mandrill.utilities.feign.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import my.com.mandrill.utilities.general.dto.model.EventEntityDTO;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWithAuthoritiesDTO extends EventEntityDTO implements Serializable {

	private String id;

	private Set<UserInstitutionAuthorityIdsDTO> userInstitutionAuthorities;

}
