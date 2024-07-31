package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DocumentTypeEnum {

	UTILITY_TYPE("UTILITY_TYPE", "Utility Type"), IDENTITY_TYPE("IDENTITY_TYPE", "Identity Type"),
	FREE_TEXT("FREE_TEXT", "Free Text"), PROPERTY_TYPE("PROPERTY_TYPE", "Property Type"),
	VEHICLE_TYPE("VEHICLE_TYPE", "Vehicle Type"), FINANCE_TYPE("FINANCE_TYPE", "Finance Type"),
	LEGAL_TYPE("LEGAL_TYPE", "Legal Type"), INSURANCE_TYPE("INSURANCE_TYPE", "Insurance Type");

	private final String name;

	private final String description;

}
