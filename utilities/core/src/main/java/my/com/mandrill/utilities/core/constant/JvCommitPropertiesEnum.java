package my.com.mandrill.utilities.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JvCommitPropertiesEnum {

	INSTITUTION_ID("institutionId"), USER_EMAIL("userEmail"), USER_FULL_NAME("userFullName");

	private String key;

}
