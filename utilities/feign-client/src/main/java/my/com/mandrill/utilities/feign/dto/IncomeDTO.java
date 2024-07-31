package my.com.mandrill.utilities.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class IncomeDTO implements Serializable {

	private String id;

	private IncomeTypeDTO incomeType;

	private BigDecimal monthlyIncomeAmount;

}