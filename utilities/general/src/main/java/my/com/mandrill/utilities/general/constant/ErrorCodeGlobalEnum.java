package my.com.mandrill.utilities.general.constant;

import lombok.Getter;

@Getter
public enum ErrorCodeGlobalEnum {

	RUNTIME_EXCEPTION("99999", "Unknown error occurred, please contact system administrator for help (0000000 )."),
	UNKNOWN_HOST_EXCEPTION("77777", "%s service is down, please contact system administrator for help (0000000)."),

	INVALID_ARGUMENT("8888", "Invalid Argument"),
	// Access Related Error
	HTTP_MESSAGE_NOT_READABLE("50000", "Unable to retrieve information from server"),
	HTTP_ACCESS_DENIED("50001", "You are not allowed to access this page"),
	WRONG_CREDENTIALS("50002", "Invalid credentials"),
	EXCEEDED_LOGIN_ATTEMPT("50003",
			"Your account has been locked due to consecutive failed login attempts. Please proceed to reset your account password."),
	INSTITUTION_ACCESS_DENIED("50004", "You are not authorized to access data from this institution."),
	// DB Related Error
	CONSTRAINT_VIOLATION("40000", "DB constraint error occurred."), ENTITY_NOT_FOUND("40001", "Record not found: "),

	// Generic Error
	USER_NOT_VERIFIED("60000", "User not verified"), NOT_SUPPORTED("60001", "Operation not supported"),
	FAILED_TO_EXTRACT_PUBLIC_KEY("60002", "Failed to extract public key"),
	FAILED_TO_VERIFY_SIGNATURE("60003", "Failed to verify signature"),
	INVALID_PHONE_NUMBER("60004", "Invalid phone number"),
	ACCOUNT_DELETED("60005",
			"This account had been deleted. If you wish to cancel the deletion, please send email to support@moneyx.com.my to enable the account."),
	ACCOUNT_DOES_NOT_EXIST("60006", "Account does not exist. Please contact administrator for help."),
	VALIDATE_INSTITUTION_RESPONSE_FIELD_INVALID_DATA_TYPE("60007",
			"Invalid Data Type, Response field type must be " + "String.");

	private final String code;

	private final String description;

	ErrorCodeGlobalEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

}
