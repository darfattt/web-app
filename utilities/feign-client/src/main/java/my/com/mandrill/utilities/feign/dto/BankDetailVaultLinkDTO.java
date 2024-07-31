package my.com.mandrill.utilities.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BankDetailVaultLinkDTO {

	private String id;

	private BankAccountTypeDTO accountType;

	private CreditCardTypeDTO creditCardType;

	private BankListDTO bankList;

}
