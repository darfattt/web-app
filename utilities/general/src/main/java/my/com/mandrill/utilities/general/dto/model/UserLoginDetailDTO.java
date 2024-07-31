package my.com.mandrill.utilities.general.dto.model;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.com.mandrill.utilities.general.util.ObjectMapperUtil;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDetailDTO {

	private String username;

	private List<String> institutionIds;

	@Override
	public String toString() {

		try {
			return ObjectMapperUtil.MAPPER.writeValueAsString(this);
		}
		catch (JsonProcessingException e) {
			return super.toString();
		}
	}

}
