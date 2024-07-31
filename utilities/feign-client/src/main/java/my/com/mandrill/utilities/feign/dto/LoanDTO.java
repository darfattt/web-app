package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import my.com.mandrill.utilities.general.constant.EntityName;
import my.com.mandrill.utilities.general.constant.LoanTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanDTO implements Serializable {

	private String id;

	private BankListDTO provider;

	private Short duration;

	private Month repaymentStartMonth;

	@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT, pattern = "yyyy")
	private Year repaymentStartYear;

	private BigDecimal monthlyInstallment;

	private BigDecimal interestRate;

	private EntityName entityName;

	private String entityId;

	private LoanTypeEnum type;

	private BigDecimal amount;

	private BigDecimal percentage;

	private String label;

}
