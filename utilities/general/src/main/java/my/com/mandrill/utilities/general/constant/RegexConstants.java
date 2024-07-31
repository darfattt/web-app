package my.com.mandrill.utilities.general.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegexConstants {

	public static final String NUMBER = "^[0-9]+$";

	public static final String NUMBER_MESSAGE = "Only accept numerical value";

	public static final String ONLY_NINE = "^9*$";

}
