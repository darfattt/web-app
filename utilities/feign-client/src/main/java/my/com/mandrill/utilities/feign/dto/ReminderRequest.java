package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import my.com.mandrill.utilities.general.constant.DeliveryType;
import my.com.mandrill.utilities.general.constant.ReminderFrequency;
import my.com.mandrill.utilities.general.constant.ReminderType;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReminderRequest implements Serializable {

	private String id;

	private DeliveryType deliveryType;

	private ReminderType reminderType;

	private LocalDate startDate;

	private LocalDate endDate;

	private ReminderFrequency reminderFrequency;

	private ObjectRequest data;

	private ExpenseDTO expense;

	private IncomeDTO income;

	private BankDetailDTO bankDetail;

	private PropertyDTO property;

	private VehicleDTO vehicle;

	private InsuranceDTO insurance;

	private UtilityDTO utility;

	private LoanDTO loan;

	private AttachmentGroupDTO attachmentGroup;

}