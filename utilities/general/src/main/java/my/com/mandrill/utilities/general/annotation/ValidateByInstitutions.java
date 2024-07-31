package my.com.mandrill.utilities.general.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import my.com.mandrill.utilities.general.constant.ValidateInstitutionRequestObjectEnum;
import my.com.mandrill.utilities.general.constant.ValidateInstitutionResponseObjectEnum;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface ValidateByInstitutions {

	boolean validateRequest() default false;

	String parameterRequestName() default "";

	boolean validateResponse() default false;

	String responseFieldName() default "";

	ValidateInstitutionResponseObjectEnum responseObjectType() default ValidateInstitutionResponseObjectEnum.NONE;

	ValidateInstitutionRequestObjectEnum requestObjectType() default ValidateInstitutionRequestObjectEnum.NONE;

}
