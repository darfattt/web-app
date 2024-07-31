package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExpenseTypeEnum {

	FIXED("FIXED", "Fixed"), VARIABLE("VARIABLE", "Variable");

	private final String code;

	private final String description;

	public ExpenseTypeEnum.ExpenseTypeEnumDTO getObject() {
		return ExpenseTypeEnum.ExpenseTypeEnumDTO.builder().code(code).description(description).build();
	}

	@AllArgsConstructor
	@Data
	@Builder
	public static class ExpenseTypeEnumDTO {

		private String code;

		private String description;

	}

}
