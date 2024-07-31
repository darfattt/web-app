package my.com.mandrill.utilities.core.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority(@authorityPermission.REPORT_READ) && hasAuthority(@authorityPermission.REPORT_READ_USER_INTERESTED)")
public @interface HasUserInterestedAccess {

}
