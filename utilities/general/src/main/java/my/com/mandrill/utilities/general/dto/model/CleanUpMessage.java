package my.com.mandrill.utilities.general.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import my.com.mandrill.utilities.general.constant.ReminderType;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CleanUpMessage implements Serializable {

	private String userId;

	private List<String> ids;

	private ReminderType reminderType;

	private Instant timeTriggered;

}
