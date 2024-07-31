package my.com.mandrill.utilities.core.annotation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import my.com.mandrill.utilities.general.util.RequestUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation is for adding custom headers needed for
 * SignatureChallengeOrPasswordFilter Class in swagger docs
 */
@Retention(RetentionPolicy.RUNTIME)
@Parameters(value = { @Parameter(in = ParameterIn.HEADER, name = RequestUtil.SIGNATURE_CHALLENGE),
		@Parameter(in = ParameterIn.HEADER, name = RequestUtil.CREDENTIAL) })
public @interface SignatureChallengeOrPassword {

}
