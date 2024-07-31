package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserAdvertisementTypeEnum {

	ENGAGEMENT("ENGAGEMENT", "Engagement"), REGISTRATION("REGISTRATION", "Registration");

	private final String code;

	private final String description;

}
