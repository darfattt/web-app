package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LoanTypeEnum {

	PERSONAL_LOANS("PERSONAL_LOANS", "Personal Loan", false), HOME_LOANS("HOME_LOANS", "Home Loan", false),
	AUTO_LOANS("AUTO_LOANS", "Auto Loan", false), EDUCATION_LOANS("EDUCATION_LOANS", "Education Loan", false),
	BUSINESS_LOANS("BUSINESS_LOANS", "Business Loan", false),
	CREDIT_CARD_BALANCE_TRANSFER("CREDIT_CARD_BALANCE_TRANSFER", "Credit Card Balance Conversion/Transfer", false),
	ISLAMIC_FINANCING("ISLAMIC_FINANCING", "Islamic Financing Loan", false),
	OTHER_LOAN("OTHER_LOAN", "Other Loan", true);

	private final String code;

	private final String name;

	private final Boolean others;

	public LoanTypeEnum.LoanTypeEnumDTO getObject() {
		return LoanTypeEnum.LoanTypeEnumDTO.builder().code(code).name(name).others(others).build();
	}

	@AllArgsConstructor
	@Data
	@Builder
	public static class LoanTypeEnumDTO {

		private String code;

		private String name;

		private Boolean others;

	}

}
