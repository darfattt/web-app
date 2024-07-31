package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JourneyConfigurationGroupType {

	BANK("BANK", "Bank", "Bank"), INSURANCE("INSURANCE", "Insurance", "Insurance"),
	CREDIT_CARD("CREDIT_CARD", "Credit Card", "Credit Card"), LOAN("LOAN", "Loan", "Loan"),
	PROPERTY("PROPERTY", "Property", "Property"), VEHICLE("VEHICLE", "Vehicle", "Vehicle");

	private final String code;

	private final String name;

	private final String description;

	public JourneyConfigurationGroupType.JourneyConfigurationGroupTypeDTO getObject() {
		return JourneyConfigurationGroupTypeDTO.builder().code(code).name(name).description(description).build();
	}

	@AllArgsConstructor
	@Data
	@Builder
	public static class JourneyConfigurationGroupTypeDTO {

		private String code;

		private String name;

		private String description;

	}

}
