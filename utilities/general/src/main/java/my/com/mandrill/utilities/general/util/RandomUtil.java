package my.com.mandrill.utilities.general.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RandomUtil {

	public static String generateAlphanumeric(int count) {
		return RandomStringUtils.randomAlphanumeric(count);
	}

	public static String generateAlphanumeric(int min, int max) {
		return RandomStringUtils.randomAlphanumeric(min, max);
	}

	public static String generateNumber(int count) {
		return RandomStringUtils.randomNumeric(count);
	}

	public static String generateNumber(int min, int max) {
		return RandomStringUtils.randomNumeric(min, max);
	}

}
