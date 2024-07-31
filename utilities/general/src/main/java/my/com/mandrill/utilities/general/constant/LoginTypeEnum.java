package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginTypeEnum {

	ADMIN("ADMIN", "Access admin portal only"), USER("USER", "Access user portal only");

	private final String code;

	private final String description;

}
