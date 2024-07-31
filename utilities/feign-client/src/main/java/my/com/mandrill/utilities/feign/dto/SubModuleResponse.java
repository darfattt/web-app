package my.com.mandrill.utilities.feign.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubModuleResponse implements Serializable {

	private String id;

	@NotBlank
	private String code;

	private String routes;

	// sub module child
	private List<SubModuleResponse> subModuleResponses;

	private Boolean active;

	private Integer sequence;

}
