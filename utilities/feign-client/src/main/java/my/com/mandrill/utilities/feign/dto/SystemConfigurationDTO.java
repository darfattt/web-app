package my.com.mandrill.utilities.feign.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemConfigurationDTO {

	private String id;

	private String code;

	private String description;

	private String value;

	private Boolean active;

	private String institutionId;

}
