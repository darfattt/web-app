package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KafkaTopic {

	LOW_PARTITIONS(3, 1), MEDIUM_PARTITIONS(6, 1), HIGH_PARTITIONS(10, 1);

	public static final String REMINDER_CLEAN_UP_TOPIC = "reminder-clean-up";

	public static final String SEND_SMS_TOPIC = "send-sms";

	public static final String DASHBOARD_ACTIVITY = "dashboard-activity";

	private final int partitions;

	private final int replicas;

}
