package my.com.mandrill.utilities.general.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constant {

	public static final String LOGIN_REGEX = "^[_.+@A-Za-z0-9-]*$";

	public static final String SYSTEM_ACCOUNT = "system";

	public static final String ANONYMOUS_USER = "anonymous";

	public static final String ACCESS_TYPE = "accessType";

	public static final String LOGIN_TYPE = "loginType";

	public static final String LOGIN_TYPE_USER = "USER";

	public static final String LOGIN_TYPE_ADMIN = "ADMIN";

	public static final DecimalFormat moneyDecimalFormat = new DecimalFormat("#.00");

	public static final String LABEL_DELIMITER = " - ";

	public static final String SUPER_FIELD = "super.";

}
