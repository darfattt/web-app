package my.com.mandrill.utilities.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDTO implements Serializable {

	private String id;

	private String name;

	private ObjectDTO institution;

	private Set<PermissionDTO> permissions = new HashSet<>();

	private Boolean active;

	private String component;

}
