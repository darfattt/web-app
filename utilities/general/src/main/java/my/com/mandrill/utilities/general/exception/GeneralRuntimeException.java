package my.com.mandrill.utilities.general.exception;

import my.com.mandrill.utilities.general.constant.ErrorCodeGlobalEnum;
import org.springframework.http.HttpStatus;

public class GeneralRuntimeException extends RuntimeException {

	private static final String EXCEPTION_CODE = ErrorCodeGlobalEnum.RUNTIME_EXCEPTION.getCode();

	private static final String DESCRIPTION = ErrorCodeGlobalEnum.RUNTIME_EXCEPTION.getDescription();

	private static final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

	public GeneralRuntimeException(String message) {
		super(message);
	}

	public ApiError getApiError() {
		return new ApiError(HTTP_STATUS, EXCEPTION_CODE, DESCRIPTION, this);
	}

}
