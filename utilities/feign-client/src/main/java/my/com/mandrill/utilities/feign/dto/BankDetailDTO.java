package my.com.mandrill.utilities.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.com.mandrill.utilities.general.constant.AccountType;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BankDetailDTO implements Serializable {

	private String id;

	private String label;

	private AccountType accountType;

	private BigDecimal savingsAmount;

	private String bankCardExpirationMonth;

	private String bankCardExpirationYear;

	private BigDecimal fixedDepositAmount;

	private CreditCardTypeDTO creditCardType;

	private String creditCardTypeExpiryMonth;

	private String creditCardTypeExpiryYear;

	private String cardProduct;

	private BigDecimal creditCardLimit;

	private Integer tenure;

	private String fixedDepositMaturityMonth;

	private String fixedDepositMaturityYear;

	private String fixedDepositStartMonth;

	private String fixedDepositStartYear;

	private BigDecimal fixedDepositInterestRate;

	private Boolean reminder;

	private String attachmentGroupId;

}
