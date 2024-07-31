package my.com.mandrill.utilities.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UtilityDTO implements Serializable {

	private String id;

	private String userId;

	private String accountNumber;

	private String accountLabel;

	private String address1;

	private String address2;

	private String address3;

	private LocalDate paymentDueDate;

	private BigDecimal paymentAmount;

	private Boolean favourite;

	private String utilityTypeId;

	private String attachmentGroupId;

	private Boolean active;

	private UtilityTypeDTO utilityType;

}
