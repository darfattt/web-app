package my.com.mandrill.utilities.feign.dto;

import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BankResponse implements Serializable {

	@Digits(integer = 15, fraction = 2)
	private BigDecimal totalIncomeAmount;

	@Digits(integer = 15, fraction = 2)
	private BigDecimal totalExpenseAmount;

	private List<BankDTO> banks;

}
