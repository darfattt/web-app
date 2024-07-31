package my.com.mandrill.utilities.general.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.ZoneId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeConstant {

	public static final String DEFAULT_TIMEZONE = "Asia/Kuala_Lumpur";

	public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of(DEFAULT_TIMEZONE);

	public static final String APPLICATION_TIMEZONE = "UTC";

}
