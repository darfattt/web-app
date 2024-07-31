package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JourneyConfigurationGroupDTO implements Serializable {

	private String id;

	@NotNull
	@NotBlank
	private String name;

	@NotBlank
	private String description;

	@NotNull
	private Boolean active;

}
