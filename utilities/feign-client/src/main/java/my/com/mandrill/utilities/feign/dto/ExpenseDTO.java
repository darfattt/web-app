package my.com.mandrill.utilities.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO implements Serializable {

	private String id;

	private BigDecimal amount;

	private String label;

	private Boolean active;

	private ExpenseTypeDTO expenseType;

}
