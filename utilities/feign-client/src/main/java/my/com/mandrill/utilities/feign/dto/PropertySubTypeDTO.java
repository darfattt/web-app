package my.com.mandrill.utilities.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PropertySubTypeDTO implements Serializable {

	private String id;

	private String code;

	private String name;

	private String description;

	private Boolean active;

}
