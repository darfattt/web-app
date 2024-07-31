package my.com.mandrill.utilities.general.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum SystemConfigurationEnum {

	DEFAULT_INSTITUTION_TIER_MAX("DEFAULT_INSTITUTION_TIER_MAX", "Default Institution Tier Max", "3"),
	FAIL_LOGIN_ATTEMPT("FAIL_LOGIN_ATTEMPT", "Fail Login Attempt", "3"),
	REQUEST_OTP_ATTEMPT("REQUEST_OTP_ATTEMPT", "Fail Request OTP Attempt", "2"),
	DEFAULT_USER_INSTITUTION_ID("DEFAULT_USER_INSTITUTION_ID", "Default Institution for user self registration",
			"97a0a02a-02ef-4616-96db-a1abf22a8b40"),
	DEFAULT_CURRENCY_ID("DEFAULT_CURRENCY_ID", "Default Currency Id", "823612b9-ada2-11ed-a899-0242ac120002"),
	DEFAULT_CURRENCY("DEFAULT_CURRENCY", "Default Currency", "MYR"),
	DEFAULT_COUNTRY("DEFAULT_COUNTRY", "Default Country", "MY"),
	DEFAULT_YEAR_RANGE_OF_EXPIRY_CARD_PICKLIST("DEFAULT_YEAR_RANGE_OF_EXPIRY_CARD_PICKLIST",
			"Default Year Range Of Expiry Card Picklist", "20"),
	REMINDER_TITLE("REMINDER_TITLE", "Reminder title format", "%s - Reminder"),
	REMINDER_CONTENT("REMINDER_CONTENT", "Reminder content format", "There is new reminder action due today %s - %s"),
	SNOOZE_MAXIMUM_DAYS("SNOOZE_MAXIMUM_DAYS", "Maximum number of days for snooze", "3"),
	DEFAULT_MERCHANT_ID("DEFAULT_MERCHANT_ID", "Default Merchant ID", "601b5473-2cdd-4c90-b94e-0af572bab614"),
	MAXIMUM_NUMBER_OF_DEVICES_PER_USER("MAXIMUM_NUMBER_OF_DEVICES_PER_USER", "Maximum number of devices per user", "2"),
	SIGNATURE_CHALLENGE_EXPIRED_TIME_IN_SECOND("SIGNATURE_CHALLENGE_EXPIRED_TIME_IN_SECOND",
			"Signature challenge expired time in seconds", "60"),
	DASHBOARD_DATE_LENGTH("DASHBOARD_DATE_LENGTH", "Dashboard Date Maximum Length", "7"),
	TERM_CONDITION_VERSION("TERM_CONDITION_VERSION", "Term and Condition Version", "v0.00.00"),
	PRIVACY_POLICY_VERSION("PRIVACY_POLICY_VERSION", "Privacy Policy Version", "v0.00.00");

	private final String code;

	private final String description;

	private final String value;

	SystemConfigurationEnum(String code, String description, String value) {
		this.code = code;
		this.description = description;
		this.value = value;
	}

	public static Optional<SystemConfigurationEnum> filterByCode(String code) {

		return Arrays.stream(SystemConfigurationEnum.class.getEnumConstants())
				.filter(systemConfigurationEnum -> systemConfigurationEnum.getCode().equals(code)).findFirst();
	}

	public Long getLong() {
		return Long.parseLong(this.value);
	}

	public Integer getInteger() {
		return Integer.parseInt(this.value);
	}

	@Override
	public String toString() {
		return "SystemConfigurationEnum{" + "code='" + code + '\'' + ", description='" + description + '\'' + ", value="
				+ value + '}';
	}

}
