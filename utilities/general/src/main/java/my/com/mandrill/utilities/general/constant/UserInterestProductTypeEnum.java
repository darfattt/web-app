package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserInterestProductTypeEnum {

	CREDIT_CARD("credit-card", "Credit Card"), PERSONAL_LOAN("personal-loan", "Personal Loan"),
	HOME_LOAN("home-loan", "Home Loan"), CAR_LOAN("car-loan", "Car Loan"),
	BUSINESS_LOAN("business-loan", "Business Loan"), SAVINGS_ACCOUNT("savings-account", "Savings Account"),
	FIXED_DEPOSIT("fixed-deposit", "Fixed Deposit"), CURRENT_ACCOUNT("current-account", "Current Account"),
	ISLAMIC_FIXED_DEPOSIT("islamic-fixed-deposit", "Islamic Fixed Deposit"),
	CAR_INSURANCE("car-insurance", "Car Insurance"), PROPERTY_INSURANCE("property-insurance", "Property Insurance"),
	TERM_LIFE_INSURANCE("term-life-insurance", "Term Life Insurance"),
	WHOLE_LIFE_INSURANCE("whole-life-insurance", "Whole Life Insurance"),
	MEDICAL_CARD_INSURANCE("medical-card-insurance", "Medical Card Insurance"),
	CRITICAL_ILLNESS_INSURANCE("critical-illness-insurance", "Critical Illness Insurance"),
	TRAVEL_INSURANCE("travel-insurance", "Travel Insurance"),
	PERSONAL_ACCIDENT_INSURANCE("personal-accident-insurance", "Personal Accident Insurance");

	private final String code;

	private final String name;

	public static boolean isExist(String key) {
		for (UserInterestProductTypeEnum typeEnum : values()) {
			if (String.valueOf(typeEnum.code).equals(key)) {
				return true;
			}
		}
		return false;
	}

	public static String getProductTypeName(String key) {
		String result = null;
		for (UserInterestProductTypeEnum typeEnum : values()) {
			if (String.valueOf(typeEnum.code).equals(key)) {
				result = typeEnum.getName();
			}
		}
		return result;
	}

}
