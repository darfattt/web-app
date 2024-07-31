package my.com.mandrill.utilities.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.com.mandrill.utilities.general.constant.ExpenseTypeEnum;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExpenseTypeDTO implements Serializable {

	private String id;

	private String name;

	private String description;

	private ExpenseTypeEnum type;

	private Boolean isUtility;

}
