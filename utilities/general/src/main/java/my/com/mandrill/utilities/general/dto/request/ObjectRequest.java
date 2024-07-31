package my.com.mandrill.utilities.general.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectRequest implements Serializable {

	@NotBlank
	@Size(max = 36)
	private String id;

}
