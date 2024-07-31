package my.com.mandrill.utilities.general.exception;

import feign.RetryableException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.general.constant.ErrorCodeGlobalEnum;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	public static ResponseEntity<Object> handleMethodDefault(@NonNull MethodArgumentNotValidException ex,
			@NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
		List<ApiValidationError> apiSubErrors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String objectName = error.getObjectName();
			String fieldName = ((FieldError) error).getField();
			Object rejectedValue = ((FieldError) error).getRejectedValue();
			String errorMessage = error.getDefaultMessage();
			apiSubErrors.add(new ApiValidationError(objectName, fieldName, rejectedValue, errorMessage));
		});
		return buildResponseEntity(
				new ApiError(HttpStatus.BAD_REQUEST, ErrorCodeGlobalEnum.HTTP_MESSAGE_NOT_READABLE.getCode(),
						ErrorCodeGlobalEnum.HTTP_MESSAGE_NOT_READABLE.getDescription(), apiSubErrors));
	}

	private static ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		log.error("GlobalExceptionHandler");
		log.error("Code: " + apiError.getErrorCode());
		log.error("Status: " + apiError.getStatus().toString());
		log.error("Message: " + apiError.getMessage());
		log.error("Debug Message: " + apiError.getDebugMessage());
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@Override
	@NonNull
	protected ResponseEntity<Object> handleHttpMessageNotReadable(@NonNull HttpMessageNotReadableException ex,
			@NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
		return buildResponseEntity(
				new ApiError(HttpStatus.BAD_REQUEST, ErrorCodeGlobalEnum.HTTP_MESSAGE_NOT_READABLE.getCode(),
						ErrorCodeGlobalEnum.HTTP_MESSAGE_NOT_READABLE.getDescription(), ex));
	}

	@Override
	@NonNull
	protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
			@NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
		return handleMethodDefault(ex, headers, status, request);
	}

	@ExceptionHandler(AccessDeniedException.class)
	protected ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex) {
		return buildResponseEntity(new ApiError(HttpStatus.FORBIDDEN, ErrorCodeGlobalEnum.HTTP_ACCESS_DENIED.getCode(),
				ErrorCodeGlobalEnum.HTTP_ACCESS_DENIED.getDescription(), ex));
	}

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
		return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ErrorCodeGlobalEnum.ENTITY_NOT_FOUND.getCode(),
				ErrorCodeGlobalEnum.ENTITY_NOT_FOUND.getDescription() + ex.getLocalizedMessage(), ex));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleTransactionSystemException(DataIntegrityViolationException ex) {
		return buildResponseEntity(
				new ApiError(HttpStatus.BAD_REQUEST, ErrorCodeGlobalEnum.CONSTRAINT_VIOLATION.getCode(),
						ErrorCodeGlobalEnum.CONSTRAINT_VIOLATION.getDescription(), ex));

	}

	@ExceptionHandler(ApiErrorException.class)
	protected ResponseEntity<Object> handleApiErrorException(ApiErrorException ex) {
		return buildResponseEntity(ex.getApiError());
	}

	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<Object> handleRuntime(RuntimeException ex) {
		return buildResponseEntity(
				new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodeGlobalEnum.RUNTIME_EXCEPTION.getCode(),
						ErrorCodeGlobalEnum.RUNTIME_EXCEPTION.getDescription(), ex));
	}

	@ExceptionHandler(RetryableException.class)
	protected ResponseEntity<Object> handleRetryableException(RetryableException ex) {
		if (ex.getCause() instanceof UnknownHostException unknownHostException) {
			String description = String.format(ErrorCodeGlobalEnum.UNKNOWN_HOST_EXCEPTION.getDescription(),
					unknownHostException.getMessage());
			return buildResponseEntity(new ApiError(HttpStatus.SERVICE_UNAVAILABLE,
					ErrorCodeGlobalEnum.UNKNOWN_HOST_EXCEPTION.getCode(), description, unknownHostException));
		}
		return handleRuntime(ex);
	}

	@ExceptionHandler(BadCredentialsException.class)
	protected ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
		return buildResponseEntity(
				new ApiError(HttpStatus.UNAUTHORIZED, ErrorCodeGlobalEnum.WRONG_CREDENTIALS.getCode(),
						ErrorCodeGlobalEnum.WRONG_CREDENTIALS.getDescription() + " " + ex.getLocalizedMessage(), ex));
	}

	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<Object> handleBusinessException(BusinessException ex) {
		return buildResponseEntity(ex.getApiError());
	}

	@ExceptionHandler(NumberFormatException.class)
	protected ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex) {
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ErrorCodeGlobalEnum.RUNTIME_EXCEPTION.getCode(),
				ErrorCodeGlobalEnum.RUNTIME_EXCEPTION.getDescription(), ex));
	}

	@ExceptionHandler(GlobalNotSupportedException.class)
	protected ResponseEntity<Object> handleGlobalNotSupportedException(GlobalNotSupportedException ex) {
		return buildResponseEntity(ex.getApiError());
	}

	@ExceptionHandler(UnexpectedRollbackException.class)
	protected ResponseEntity<Object> handleGlobalNotSupportedException(UnexpectedRollbackException ex) {
		ex.printStackTrace();
		return handleRuntime(ex);
	}

	@ExceptionHandler(PhoneNumberException.class)
	protected ResponseEntity<Object> handlePhoneNumberException(PhoneNumberException ex) {
		return buildResponseEntity(ex.getApiError());
	}

}
