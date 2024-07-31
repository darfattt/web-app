package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import my.com.mandrill.utilities.general.constant.ReminderFrequency;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InsuranceDTO implements Serializable {

	private String id;

	private ObjectDTO provider;

	private LocalDate renewalDate;

	private String entityId;

	private ObjectDTO product;

	private String type;

	private ObjectDTO insuranceType;

	private ReminderFrequency paymentFrequency;

	private Boolean isReminder;

	private BigDecimal coverageLimit;

	private BigDecimal premium;

}