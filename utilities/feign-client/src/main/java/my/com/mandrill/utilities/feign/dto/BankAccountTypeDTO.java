package my.com.mandrill.utilities.feign.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BankAccountTypeDTO implements Serializable {

	private String id;

	private String code;

	private String name;

	private String description;

	private Boolean active;

}
