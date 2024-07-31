package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthorityEnum {

	ADMIN("ADMIN", "admin"), EMPLOYEE("EMPLOYEE", "employee"), VIEWER("VIEWER", "viewer"),
	SITE_ACCESS("SITE_ACCESS", "site access"), PCM_ADMIN("PCM_ADMIN", "PCM Admin"),
	COORDINATOR("COORDINATOR", "Coordinator"), CLASSIFIER("CLASSIFIER", "Classifier"),
	SUPER_USER("SUPER_USER", "Super User"), USER("USER", "User");

	private final String code;

	private final String description;

}
