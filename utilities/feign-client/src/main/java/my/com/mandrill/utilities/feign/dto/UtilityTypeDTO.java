package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UtilityTypeDTO implements Serializable {

	private String id;

	private String name;

	private String description;

}