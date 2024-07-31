package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EntityName {

	VEHICLE("VEHICLE", "Vehicle"), PROPERTY("PROPERTY", "Property"),
	PROPERTY_STAGING("PROPERTY_STAGING", "Property Staging"), UTILITY("UTILITY", "Utility"), BANK("BANK", "Bank"),
	CREDIT_CARD("CREDIT_CARD", "Credit Card"), INSURANCE("INSURANCE", "Insurance"), LOAN("LOAN", "Loan"),
	BANKLIST("BANKLIST", "Bank List"), USER("USER", "User"), LEGAL("LEGAL", "Legal"),
	GET_TO_KNOW_CC("GET_TO_KNOW_CC", "Get To Know Credit Card"), ADVERTISEMENT("ADVERTISEMENT", "Advertisement");

	private final String code;

	private final String name;

}
