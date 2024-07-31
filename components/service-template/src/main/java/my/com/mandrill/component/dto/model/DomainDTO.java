package my.com.mandrill.component.dto.model;

import lombok.Data;
import my.com.mandrill.component.constant.ConstantEnum;

import java.io.Serializable;

@Data
public class DomainDTO implements Serializable {

	private String id;

	private String code;

	private String name;

	private String description;

	private ConstantEnum type;

}
