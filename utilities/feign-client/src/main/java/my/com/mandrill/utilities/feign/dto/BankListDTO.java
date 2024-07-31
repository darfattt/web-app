package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankListDTO implements Serializable {

	private String id;

	private String code;

	private String name;

	private String description;

	@Builder.Default
	private Boolean active = true;

	private String attachmentGroupId;

	private String url;

	private String colorCode;

}
