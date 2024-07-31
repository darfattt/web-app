package my.com.mandrill.utilities.general.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import my.com.mandrill.utilities.general.constant.ErrorCodeGlobalEnum;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class GlobalNotSupportedException extends RuntimeException {

	private final ApiError apiError;

	public GlobalNotSupportedException(String message) {
		super(message);
		apiError = new ApiError(HttpStatus.NOT_IMPLEMENTED, ErrorCodeGlobalEnum.NOT_SUPPORTED.getCode(),
				ErrorCodeGlobalEnum.NOT_SUPPORTED.getDescription(), this);
	}

}
