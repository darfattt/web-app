package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import my.com.mandrill.utilities.general.constant.DeliveryType;
import my.com.mandrill.utilities.general.constant.ReminderFrequency;
import my.com.mandrill.utilities.general.constant.ReminderType;

import java.io.Serializable;
import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReminderResponse implements Serializable {

	private String id;

	private DeliveryType deliveryType;

	private ReminderType reminderType;

	private Instant startDate;

	private Instant endDate;

	private ReminderFrequency reminderFrequency;

	private ReminderDataResponse data;

	private Boolean isIntegration;

}