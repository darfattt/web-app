package my.com.mandrill.utilities.general.constant;

import lombok.Getter;

@Getter
public enum EmailTemplateVariableEnum {

	USERNAME("username"), URL("url"), FIRST_NAME("firstName"), LAST_MODIFIED_DATE("lastModifiedDate"), OTP("otp"),
	REGISTRATION_URL("registrationUrl"), RECIPIENT_NAME("recipientName"), REQUESTER_COMPANY("requesterCompany"),
	RECIPIENT_COMPANY("recipientCompany"), INSTITUTION_TYPE("institutionType"),
	SITE_ENTITY_REGISTRATION_URL("siteEntityRegistrationUrl"), FIRST_LOGIN_URL("firstLoginUrl"), SITE_NAME("siteName"),
	PREVIOUS_ROLE("previousRole"), NEW_ROLE("newRole");

	private final String value;

	EmailTemplateVariableEnum(String value) {
		this.value = value;
	}

}
