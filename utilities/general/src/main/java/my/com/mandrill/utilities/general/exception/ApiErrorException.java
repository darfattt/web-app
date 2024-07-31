package my.com.mandrill.utilities.general.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.lang.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiErrorException extends RuntimeException {

	private final ApiError apiError;

	public ApiErrorException(@NonNull ApiError apiError) {
		this.apiError = apiError;
	}

}
