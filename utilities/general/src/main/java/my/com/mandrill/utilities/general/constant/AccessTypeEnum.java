package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccessTypeEnum {

	EMAIL("EMAIL", "login with email"), MOBILE("MOBILE", "login with mobile"),
	STAFF_ID("STAFF_ID", "login with staff id");

	private final String code;

	private final String description;

}
