package my.com.mandrill.utilities.general.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NumberUtil {

	public static final DecimalFormat ZERO_DECIMAL = new DecimalFormat("0");

	static {
		ZERO_DECIMAL.setRoundingMode(RoundingMode.HALF_UP);
	}

}