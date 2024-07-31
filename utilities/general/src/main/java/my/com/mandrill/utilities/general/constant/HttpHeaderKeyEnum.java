package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpHeaderKeyEnum {

	INSTITUTION("X-Institution-Id"), SUB_INSTITUTION("X-Sub-Institution-Id");

	private String key;

}
