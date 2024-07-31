package my.com.mandrill.utilities.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO implements Serializable {

	private String id;

	private BankListDTO bankList;

	private BigDecimal combineCreditCardLimit;

	private List<BankDetailDTO> bankDetails;

}
