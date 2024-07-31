package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Getter
public enum InsuranceTypeEnum {

	MEDICAL_HEALTH("MEDICAL_HEALTH", "Medical and Health Insurance", InsuranceCategoryEnum.GENERAL),
	LIFE("LIFE", "Life Insurance", InsuranceCategoryEnum.LIFE),
	PROPERTY("PROPERTY", "Property Insurance", InsuranceCategoryEnum.GENERAL),
	VEHICLE("VEHICLE", "Automobile Insurance", InsuranceCategoryEnum.GENERAL),
	TRAVEL("TRAVEL", "Travel Insurance", InsuranceCategoryEnum.GENERAL),
	PERSONAL_ACCIDENT("PERSONAL_ACCIDENT", "Personal Accident Insurance", InsuranceCategoryEnum.GENERAL),
	OTHERS("OTHERS", "Other Insurance", InsuranceCategoryEnum.GENERAL);

	private final String code;

	private final String name;

	private final InsuranceCategoryEnum category;

	public static Optional<InsuranceTypeEnum> findByCode(String code) {
		return Arrays.stream(InsuranceTypeEnum.values())
				.filter(insuranceTypeEnum -> insuranceTypeEnum.getCode().equals(code)).findFirst();
	}

	public InsuranceTypeEnum.InsuranceTypeEnumDTO getObject() {
		return InsuranceTypeEnum.InsuranceTypeEnumDTO.builder().code(code).name(name).category(category).build();
	}

	@AllArgsConstructor
	@Data
	@Builder
	public static class InsuranceTypeEnumDTO {

		private String code;

		private String name;

		private InsuranceCategoryEnum category;

	}

}
