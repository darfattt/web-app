package my.com.mandrill.utilities.feign.dto;

import lombok.Getter;
import lombok.Setter;
import my.com.mandrill.utilities.general.constant.Language;

import java.util.Set;

@Getter
@Setter
public class UserResponse {

	private String id;

	private String refNo;

	private String username;

	private String firstName;

	private String lastName;

	private String email;

	private String phoneCountry;

	private String phoneNumber;

	private boolean emailVerified;

	private Set<String> authorities;

	private Language langKey;

	private String fullName;

}
