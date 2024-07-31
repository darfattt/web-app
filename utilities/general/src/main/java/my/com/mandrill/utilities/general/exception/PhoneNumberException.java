package my.com.mandrill.utilities.general.exception;

import my.com.mandrill.utilities.general.constant.ErrorCodeGlobalEnum;
import org.springframework.http.HttpStatus;

public class PhoneNumberException extends GeneralRuntimeException {

	private static final String EXCEPTION_CODE = ErrorCodeGlobalEnum.INVALID_PHONE_NUMBER.getCode();

	private static final String DESCRIPTION = ErrorCodeGlobalEnum.INVALID_PHONE_NUMBER.getDescription();

	private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

	public PhoneNumberException(String message) {
		super(message);
	}

	@Override
	public ApiError getApiError() {
		return new ApiError(HTTP_STATUS, EXCEPTION_CODE, DESCRIPTION, this);
	}

}
