package my.com.mandrill.utilities.feign.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseLookupDTO implements Serializable {

	private String id;

	private String code;

	private String name;

	private String description;

}
