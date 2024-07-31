package my.com.mandrill.utilities.general.constant;

import lombok.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum InsuranceCategoryEnum {

	LIFE("LIFE", "Life Insurance Business"), GENERAL("GENERAL", "General Insurance Business");

	private final String code;

	private final String name;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class InsuranceCategoryEnumDTO implements Serializable {

		private String code;

		private String name;

	}

	public static List<InsuranceCategoryEnumDTO> findAll() {
		return Arrays.stream(InsuranceCategoryEnum.values())
				.map(i -> InsuranceCategoryEnumDTO.builder().code(i.getCode()).name(i.getName()).build()).toList();
	}

}
