package my.com.mandrill.utilities.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.com.mandrill.utilities.general.constant.PurchaseStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.Month;
import java.time.Year;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PropertyDTO implements Serializable {

	private String id;

	private PropertyTypeDTO propertyType;

	private PropertySubTypeDTO propertySubType;

	private String propertySubTypeOthers;

	private BigDecimal purchaseValue;

	private PurchaseStatus purchaseStatus;

	private String loanProviderId;

	private Integer loanDuration;

	private BigDecimal loanAmount;

	private BigDecimal loanPercentage;

	private Year repaymentStartYear;

	private Month repaymentStartMonth;

	private BigDecimal monthlyInstallment;

	private BigDecimal interestRate;

	private Boolean isRented;

	private BigDecimal rentalAmount;

	private Boolean installmentReminder;

	private Integer installmentMonthlyReminder;

	private Boolean rentalReminder;

	private Integer rentalMonthlyReminder;

	private Boolean currentResidence;

	private String address1;

	private String address2;

	private String address3;

	private String label;

	private Boolean active;

	private String attachmentGroupId;

	private Instant createdDate;

}
