package my.com.mandrill.component.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import my.com.mandrill.component.constant.ConstantEnum;

import java.io.Serializable;

@Data
public class DomainRequestDTO implements Serializable {

	private String id;

	@NotBlank
	@Size(max = 100)
	private String code;

	@NotBlank
	@Size(max = 100)
	private String name;

	@Size(max = 200)
	private String description;

	@NotNull
	private ConstantEnum type;

}
