package my.com.mandrill.utilities.feign.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LookupDTO implements Serializable {

	private String id;

	private String code;

	private String name;

	private LookupDTO parentLookup;

	private String description;

	private LookupPropertyDTO property;

	public BaseLookupDTO getBaseLookupDTO() {
		return new BaseLookupDTO(this.id, this.code, this.name, this.description);
	}

}
