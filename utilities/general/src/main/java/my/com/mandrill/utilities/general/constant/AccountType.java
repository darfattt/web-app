package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType {

	LOAN("LOAN", "Loan"), SAVINGS_ACCOUNT("SAVINGS_ACCOUNT", "Savings Account"),
	CREDIT_CARD("CREDIT_CARD", "Credit Card"), FIXED_DEPOSIT("FIXED_DEPOSIT", "Fixed Deposit");

	private final String code;

	private final String name;

	public AccountTypeDTO getObject() {
		return new AccountTypeDTO(code, name);
	}

	@Data
	@AllArgsConstructor
	public static class AccountTypeDTO {

		private String code;

		private String name;

	}

}
