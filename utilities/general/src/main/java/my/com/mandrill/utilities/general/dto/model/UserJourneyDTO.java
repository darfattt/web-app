package my.com.mandrill.utilities.general.dto.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Data
public abstract class UserJourneyDTO implements Serializable {

	private String userJourneyId;

}
