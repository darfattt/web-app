package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserJourneyEnum {

	SEGMENT("SEGMENT", "Segment"), MY_INCOME("MY_INCOME", "My Income"), MY_EXPENSE("MY_EXPENSE", "My Expense"),
	IMBALANCED_INCOME_EXPENSE("IMBALANCED_INCOME_EXPENSE", "Imbalanced Income & Expenses"),
	MY_BANK("MY_BANK", "My Bank"), VEHICLE_INTRODUCTION("VEHICLE_INTRODUCTION", "Vehicle Introduction"),
	PROPERTY_INTRODUCTION("PROPERTY_INTRODUCTION", "Property Introduction"),
	PROPERTY_START_FORM("PROPERTY_START_FORM", "Property Start Form"), PROPERTY_SNP("PROPERTY_SNP", "Property SNP"),
	UTILITY_MAIN("UTILITY_MAIN", "Utility Main"), BANK_START_NORM("BANK_START_NORM", "Bank Form"),
	BANK_START_NOINTEREST("BANK_START_NOINTEREST", "Bank Form"), CC_START_NORM("CC_START_NORM", "Credit Card Form"),
	CC_START_NOINTEREST("CC_START_NOINTEREST", "Credit Card Form"), INSURANCE_FORM("INSURANCE_FORM", "Insurance Form");

	private final String name;

	private final String description;

}
