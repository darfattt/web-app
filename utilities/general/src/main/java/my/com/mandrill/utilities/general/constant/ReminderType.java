package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReminderType {

	EXPENSE("EXPENSE", "Expense", "Expense Type", null), INCOME("INCOME", "Income", "Income Type", null),
	BANK("BANK", "Bank", "Bank", null),
	VEHICLE_ROAD_TAX("VEHICLE_ROAD_TAX", "Vehicle Road Tax Renewal Date", "Vehicle Road Tax Renewal Date",
			SubType.ROAD_TAX),
	VEHICLE_INSTALMENT("VEHICLE_INSTALMENT", "Vehicle Instalment", "Vehicle Instalment", SubType.INSTALMENT),
	PROPERTY_INSTALMENT("PROPERTY_INSTALMENT", "Property Instalment", "Property Instalment", SubType.INSTALMENT),
	PROPERTY_RENTAL("PROPERTY_RENTAL", "Property Rental", "Property Rental", SubType.RENTAL),
	INSURANCE_INSTALMENT("INSURANCE_INSTALMENT", "Insurance Premium", "Insurance Premium", SubType.PREMIUM),
	UTILITY("UTILITY", "Utility", "Utility", null),
	LOAN_INSTALMENT("LOAN_INSTALMENT", "Loan Instalment", "Loan Instalment", SubType.INSTALMENT),
	VAULT("VAULT", "Vault", "Vault", null);

	private final String code;

	private final String name;

	private final String description;

	private final SubType subType;

	public ReminderType.ReminderTypeDTO getObject() {
		return ReminderTypeDTO.builder().code(code).name(name).description(description).build();
	}

	@Getter
	@AllArgsConstructor
	public enum SubType {

		INSTALMENT("Instalment"), ROAD_TAX("Road Tax"), RENTAL("Rental"), PREMIUM("Premium");

		private final String name;

	}

	@AllArgsConstructor
	@Data
	@Builder
	public static class ReminderTypeDTO {

		private String code;

		private String name;

		private String description;

	}

}
