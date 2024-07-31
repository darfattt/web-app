package my.com.mandrill.utilities.general.exception;

import lombok.Data;
import my.com.mandrill.utilities.general.constant.ErrorCodeGlobalEnum;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
public class ApiError implements Serializable {

	private String errorCode;

	private String message;

	private HttpStatus status;

	private Instant timestamp;

	private String debugMessage;

	private List<ApiValidationError> subErrors;

	private ApiError() {
		timestamp = Instant.now();
	}

	ApiError(HttpStatus status) {
		this();
		this.status = status;
	}

	ApiError(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}

	public ApiError(String errorCode, String message) {
		this();
		this.errorCode = errorCode;
		this.message = message;
	}

	ApiError(ErrorCodeGlobalEnum errorEnum) {
		this();
		this.errorCode = errorEnum.getCode();
		this.message = errorEnum.getDescription();
	}

	public ApiError(HttpStatus status, String errorCode, String message, Throwable ex) {
		this();
		this.errorCode = errorCode;
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage() == null ? ex + ":" + ex.getStackTrace()[0].toString()
				: ex.getLocalizedMessage();
	}

	ApiError(HttpStatus status, String errorCode, String message, List<ApiValidationError> subErrors) {
		this();
		this.errorCode = errorCode;
		this.status = status;
		this.message = message;
		this.subErrors = subErrors;
	}

}
