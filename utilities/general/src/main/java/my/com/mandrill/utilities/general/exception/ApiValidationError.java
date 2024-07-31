package my.com.mandrill.utilities.general.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
class ApiValidationError implements Serializable {

	private String object;

	private String field;

	private Object rejectedValue;

	private String message;

	ApiValidationError(String object, String message) {
		this.object = object;
		this.message = message;
	}

}
