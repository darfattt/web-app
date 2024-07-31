package my.com.mandrill.utilities.feign.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.general.constant.ErrorCodeGlobalEnum;
import my.com.mandrill.utilities.general.exception.ApiError;
import my.com.mandrill.utilities.general.exception.ApiErrorException;
import my.com.mandrill.utilities.general.exception.BusinessException;
import my.com.mandrill.utilities.general.exception.GlobalNotSupportedException;
import my.com.mandrill.utilities.general.util.ObjectMapperUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class ApiErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		ApiErrorException apiErrorException = extractApiErrorException(response);
		switch (response.status()) {
			case 400 -> {
				log.error("Error Occurred in Feign Client: {}", apiErrorException.getApiError());
				return new BusinessException(apiErrorException.getApiError());
			}
			case 401 -> {
				log.error("Unauthorized Request Occurred in Feign Client: {}", apiErrorException.getApiError());
				return new RuntimeException("Unauthorized Request Occurred in Feign Client");
			}
			case 403 -> {
				log.error("Access Denied Request Occurred in Feign Client: {}", apiErrorException.getApiError());
				return new AccessDeniedException("Access Denied Request Occurred in Feign Client");
			}
			case 404 -> {
				if (apiErrorException.getApiError().getErrorCode()
						.equals(ErrorCodeGlobalEnum.ENTITY_NOT_FOUND.getCode())) {
					return new EntityNotFoundException(apiErrorException.getApiError().getMessage());
				}
				log.error("Unidentified Request Occurred in Feign Client: {}", apiErrorException.getApiError());
				return new RuntimeException("Unidentified Request Occurred in Feign Client");
			}
			case 501 -> {
				log.error("Not implemented Request Occurred in Feign Client: {}", apiErrorException.getApiError());
				return new GlobalNotSupportedException(apiErrorException.getApiError());
			}
			default -> {
				log.error("Unknown Error Occurred in Feign Client: {}", apiErrorException.getApiError());
				return apiErrorException;
			}
		}
	}

	private ApiErrorException extractApiErrorException(Response response) {
		ApiError message;
		try (InputStream bodyIs = response.body().asInputStream()) {
			ObjectMapper mapper = ObjectMapperUtil.MAPPER;
			message = mapper.readValue(bodyIs, ApiError.class);
		}
		catch (IOException e) {
			log.error("IO Exception on reading exception message feign client" + e);
			log.error("Response: {}", response);
			message = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodeGlobalEnum.RUNTIME_EXCEPTION.getCode(),
					ErrorCodeGlobalEnum.RUNTIME_EXCEPTION.getDescription(), e);
		}

		return new ApiErrorException(message);
	}

}
