
package my.com.mandrill.utilities.core.validation;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import my.com.mandrill.utilities.feign.dto.*;
import my.com.mandrill.utilities.general.exception.ExceptionPredicate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.feign.client.AccountFeignClient;
import my.com.mandrill.utilities.general.annotation.ValidateByInstitutions;
import my.com.mandrill.utilities.general.constant.Constant;
import my.com.mandrill.utilities.general.constant.ErrorCodeGlobalEnum;
import my.com.mandrill.utilities.general.constant.ValidateInstitutionRequestObjectEnum;
import my.com.mandrill.utilities.general.constant.ValidateInstitutionResponseObjectEnum;
import my.com.mandrill.utilities.general.dto.model.UserLoginDetailDTO;
import my.com.mandrill.utilities.general.exception.ApiError;
import my.com.mandrill.utilities.general.exception.BusinessException;
import my.com.mandrill.utilities.general.util.SecurityUtil;

@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class ValidateByInstitutionsAspect {

	private final AccountFeignClient accountFeignClient;

	@Around("@annotation(validateByInstitutions)")
	public Object validateInstitutionIds(ProceedingJoinPoint joinPoint, ValidateByInstitutions validateByInstitutions)
			throws Throwable {

		UserLoginDetailDTO userLoginDetailDTO = SecurityUtil.getCurrentUserLoginDetail().orElseThrow();
		List<String> institutionIds = userLoginDetailDTO.getInstitutionIds();
		ResponseEntity<?> response = (ResponseEntity) joinPoint.proceed();
		Boolean isSuperAdmin = accountFeignClient.isSuperAdminAccount(institutionIds);
		if (Boolean.TRUE.equals(isSuperAdmin)) {
			return response;
		}

		boolean isValid = false;

		if (validateByInstitutions.validateRequest()) {
			if (validateByInstitutions.parameterRequestName().isEmpty()) {
				return new ResponseEntity<>(generateApiError("Request Parameter are required"), HttpStatus.FORBIDDEN);

			}

			isValid = validateRequest(joinPoint, validateByInstitutions, institutionIds);

			if (!isValid) {
				return new ResponseEntity<>(generateApiError(institutionIds), HttpStatus.FORBIDDEN);
			}
		}
		if (validateByInstitutions.validateResponse()) {
			if (validateByInstitutions.responseFieldName().isEmpty()
					&& ValidateInstitutionResponseObjectEnum.NONE.equals(validateByInstitutions.responseObjectType())) {
				return new ResponseEntity<>(generateApiError("Response Field Name or Object Type are required"),
						HttpStatus.FORBIDDEN);

			}
			isValid = validateResponse(response, validateByInstitutions, institutionIds);
		}

		// Validate institutionIds
		if (isValid) {
			return response;
		}
		else {
			return new ResponseEntity<>(generateApiError(institutionIds), HttpStatus.FORBIDDEN);
		}
	}

	private ApiError generateApiError(String errorMessage) {
		return new ApiError(HttpStatus.FORBIDDEN, ErrorCodeGlobalEnum.INSTITUTION_ACCESS_DENIED.getCode(),
				ErrorCodeGlobalEnum.INSTITUTION_ACCESS_DENIED.getDescription(),
				new BusinessException(ErrorCodeGlobalEnum.INSTITUTION_ACCESS_DENIED, errorMessage));
	}

	private ApiError generateApiError(List<String> institutionIds) {
		String errorMessage = String.format("You're only allowed to access [%s] institutions", institutionIds);
		return new ApiError(HttpStatus.FORBIDDEN, ErrorCodeGlobalEnum.INSTITUTION_ACCESS_DENIED.getCode(),
				ErrorCodeGlobalEnum.INSTITUTION_ACCESS_DENIED.getDescription(),
				new BusinessException(ErrorCodeGlobalEnum.INSTITUTION_ACCESS_DENIED, errorMessage));
	}

	public boolean validateRequest(JoinPoint joinPoint, ValidateByInstitutions validateByInstitutions,
			List<String> institutionIds) {
		Object requestParam = getRequestParamValue(joinPoint, validateByInstitutions.parameterRequestName());
		boolean isValid = false;

		if (requestParam instanceof String paramValue) {
			ValidateInstitutionRequestObjectEnum requestObjectType = validateByInstitutions.requestObjectType();
			log.debug("Request Param [{}]", paramValue);
			log.debug("Request Object Type [{}]", requestObjectType);

			if (!ValidateInstitutionRequestObjectEnum.NONE.equals(requestObjectType)) {
				switch (requestObjectType) {
					case USER -> isValid = validateUserInstitution(paramValue, institutionIds);
					default -> log.warn("Unhandled requestObjectType: {}", requestObjectType);
				}
			}
			else {
				isValid = institutionIds.contains(paramValue);
			}
		}
		else {
			log.warn("Request parameter is not a String: {}", requestParam);
		}

		return isValid;
	}

	private boolean validateUserInstitution(String userId, List<String> institutionIds) {
		UserWithAuthoritiesDTO user = null;
		try {
			user = accountFeignClient.getUserAuthoritiesById(userId);
			if (user.getUserInstitutionAuthorities() == null) {
				log.warn("User dont have authorities", user.getUserInstitutionAuthorities());
				return false;
			}

		}
		catch (EntityNotFoundException e) {
			return true;
		}

		List<String> userInstitutionIds = user.getUserInstitutionAuthorities().stream()
				.map(UserInstitutionAuthorityIdsDTO::getInstitutionId).toList();

		log.debug("User Data Institutions [{}]", userInstitutionIds);
		log.debug("User Logged In Institutions [{}]", institutionIds);

		Set<String> userInstitutionIdSet = new HashSet<>(userInstitutionIds);
		Set<String> institutionIdSet = new HashSet<>(institutionIds);

		// Check if there is any intersection between the two sets
		userInstitutionIdSet.retainAll(institutionIdSet);

		if (!userInstitutionIdSet.isEmpty()) {
			return true;
		}
		return false;
	}

	private Object getRequestParamValue(JoinPoint joinPoint, String requestParamName) {
		if (requestParamName.isEmpty()) {
			return null;
		}

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Object[] args = joinPoint.getArgs();

		// Get the parameter names and types
		String[] parameterNames = methodSignature.getParameterNames();

		for (int i = 0; i < parameterNames.length; i++) {
			if (parameterNames[i].equals(requestParamName)) {
				return args[i];
			}
		}
		return null;
	}

	public boolean validateResponse(ResponseEntity<?> response, ValidateByInstitutions validateByInstitutions,
			List<String> institutionIds) {
		if (response == null || response.getBody() == null) {
			return true; // Default validation when response or response body is null
		}

		Object responseBody = response.getBody();
		String responseFieldName = validateByInstitutions.responseFieldName();

		if (!responseFieldName.isEmpty() && responseBody != null) {
			ValidateInstitutionResponseObjectEnum responseObjectType = validateByInstitutions.responseObjectType();

			if (!ValidateInstitutionResponseObjectEnum.NONE.equals(responseObjectType)) {
				return handleResponseObjectType(responseObjectType, responseBody, institutionIds);
			}
			else {
				return validateField(responseBody, responseFieldName, institutionIds);
			}
		}
		else {
			log.warn("Default validation when fieldToCheck is not specified");
			return true;
		}
	}

	private boolean handleResponseObjectType(ValidateInstitutionResponseObjectEnum responseObjectType,
			Object responseBody, List<String> institutionIds) {
		// switch (responseObjectType) {
		// case USER -> {
		// // Handle USER case if needed, currently no implementation yet will always
		// // return true
		// log.info("Handling USER response object type.");
		// return validateUserResponse(responseBody, institutionIds);
		// }
		// default -> {
		// log.warn("Unhandled responseObjectType: {}", responseObjectType);
		// return false;
		// }
		// }
		return true;
	}

	private boolean validateField(Object responseBody, String responseFieldName, List<String> institutionIds) {
		try {
			Field field;
			if (responseFieldName.startsWith(Constant.SUPER_FIELD)) {
				field = responseBody.getClass().getSuperclass()
						.getDeclaredField(responseFieldName.replaceAll(Constant.SUPER_FIELD, ""));
			}
			else {
				field = responseBody.getClass().getDeclaredField(responseFieldName);
			}
			field.setAccessible(true); // Make the field accessible if it is private
			Object fieldValue = field.get(responseBody);
			log.trace("Field Value... [{}]", fieldValue);

			if (fieldValue instanceof String) {
				return institutionIds.contains(fieldValue);
			}
			else {
				log.warn("Field value is not a String: {}", fieldValue);
				throw new BusinessException(ErrorCodeGlobalEnum.VALIDATE_INSTITUTION_RESPONSE_FIELD_INVALID_DATA_TYPE);
			}
		}
		catch (NoSuchFieldException | IllegalAccessException e) {
			throw new BusinessException(ErrorCodeGlobalEnum.RUNTIME_EXCEPTION, e.getMessage());
		}
	}

	private boolean validateUserResponse(Object responseBody, List<String> institutionIds) {
		// Implement the USER response type validation logic here
		// Placeholder logic, needs to be implemented based on actual requirements
		return true; // Placeholder return value
	}

}
