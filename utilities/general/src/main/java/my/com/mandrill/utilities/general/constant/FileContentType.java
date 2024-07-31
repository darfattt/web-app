package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileContentType {

	CSV("text/csv");

	private final String value;

}
