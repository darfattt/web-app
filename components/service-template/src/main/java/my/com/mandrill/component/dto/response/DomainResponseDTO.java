package my.com.mandrill.component.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import my.com.mandrill.component.dto.model.DomainDTO;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DomainResponseDTO extends DomainDTO {

	private String text;

}
