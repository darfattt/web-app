package my.com.mandrill.utilities.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardTypeDTO implements Serializable {

	private String id;

	private String code;

	private String name;

	private String description;

	private String active;

}
