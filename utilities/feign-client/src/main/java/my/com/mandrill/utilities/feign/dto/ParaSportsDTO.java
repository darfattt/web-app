package my.com.mandrill.utilities.feign.dto;

import lombok.*;
import my.com.mandrill.utilities.general.dto.model.EventEntityDTO;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParaSportsDTO extends EventEntityDTO implements Serializable {

	public ParaSportsDTO(String id) {
		this.id = id;
	}

	private String id;

	private String name;

	private String description;

}
