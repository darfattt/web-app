package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FinanceAccessEnum {

	NO_ACCESS("NO_ACCESS", "No Access"), SUBSCRIBE("SUBSCRIBE", "Subscribe"),
	NOT_SUBSCRIBE("NOT_SUBSCRIBE", "Not Subscribe");

	private final String code;

	private final String name;

}
