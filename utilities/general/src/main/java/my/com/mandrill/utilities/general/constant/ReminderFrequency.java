package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReminderFrequency {

	// Daily, Weekly, Bi-weekly, Monthly, Quarterly, Half-annually, Annually
	ONCE("ONCE", "Once", 0), DAILY("DAILY", "Daily", 1), WEEKLY("WEEKLY", "Weekly", 1),
	BI_WEEKLY("BI_WEEKLY", "Bi-weekly", 2), MONTHLY("MONTHLY", "Monthly", 1), QUARTERLY("QUARTERLY", "Quarterly", 3),
	HALF_ANNUALLY("HALF_ANNUALLY", "Half-annually", 6), ANNUALLY("ANNUALLY", "Annually", 12),
	ANNUALLY_ONE_MONTH_BEFORE("ANNUALLY_ONE_MONTH_BEFORE", "Annually one month before", 12);

	private final String code;

	private final String description;

	private final long multiplier;

}
