package my.com.mandrill.utilities.general.exception;

import my.com.mandrill.utilities.general.constant.ErrorCodeGlobalEnum;
import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ApiError error;

	public BusinessException(ErrorCodeGlobalEnum errorEnum) {
		super();
		manageError(new ApiError(errorEnum.getCode(), errorEnum.getDescription()));
	}

	public BusinessException(ErrorCodeGlobalEnum errorEnum, String message) {
		super();
		manageError(new ApiError(errorEnum.getCode(), errorEnum.getDescription()), message);
	}

	public BusinessException(ApiError error) {
		super();
		manageError(error);
	}

	public BusinessException(ExceptionEnum exceptionEnum) {
		super();
		manageError(new ApiError(exceptionEnum.getCode(), exceptionEnum.getDescription()));
	}

	public BusinessException(ExceptionEnum exceptionEnum, Object... params) {
		super();
		String message = String.format(exceptionEnum.getDescription(), params);
		manageError(new ApiError(exceptionEnum.getCode(), message));
	}

	public BusinessException(String message, Throwable cause, ApiError error) {
		super(message, cause);
		manageError(error);
	}

	public BusinessException(String message, ApiError error) {
		super(message);
		manageError(error);
	}

	public BusinessException(Throwable cause, ApiError error) {
		super(cause);
		manageError(error);
	}

	public void manageError(ApiError error) {
		error.setStatus(HttpStatus.BAD_REQUEST);
		this.error = error;
	}

	public void manageError(ApiError error, String debugMessage) {
		error.setStatus(HttpStatus.BAD_REQUEST);
		error.setDebugMessage(debugMessage);
		this.error = error;
	}

	public ApiError getApiError() {
		return this.error;
	}

}