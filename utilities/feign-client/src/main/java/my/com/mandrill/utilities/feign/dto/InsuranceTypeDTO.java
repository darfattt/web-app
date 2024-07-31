package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import my.com.mandrill.utilities.general.constant.InsuranceCategoryEnum;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InsuranceTypeDTO implements Serializable {

	private String id;

	private String code;

	private String name;

	private InsuranceCategoryEnum category;

}
