package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class InterestTypeDTO implements Serializable {

	private String id;

	@NotNull
	@NotBlank
	@Size(max = 100)
	private String name;

	@NotNull
	@Size(max = 255)
	private String description;

	@NotNull
	private Boolean active;

	private Integer sequence;

	private JourneyConfigurationGroupDTO journeyConfigurationGroup;

}
