package my.com.mandrill.utilities.feign.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassificationDTO implements Serializable {

	private String id;

	private String classificationClass;

	private String description;

}
