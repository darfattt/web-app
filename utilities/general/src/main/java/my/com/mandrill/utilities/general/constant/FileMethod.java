package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileMethod {

	PUBLIC("public"), PRIVATE("private");

	private final String method;

}
