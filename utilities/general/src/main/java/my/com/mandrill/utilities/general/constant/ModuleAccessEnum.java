package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModuleAccessEnum {

	// TODO need to standardized category naming method and need to sync with db
	COLLECT_ACCESS("COLLECT_ACCESS", "Main Menu", "Collect"),
	SMART_REPORT_ACCESS("SMART_REPORT_ACCESS", "Main Menu", "Smart Report"),
	MONITOR_ACCESS("MONITOR_ACCESS", "Main Menu", "Monitor"),
	ORGANIZATION_ACCESS("ORGANIZATION_ACCESS", "Organisation", "Organisation"),
	SETTINGS_ACCESS("SETTINGS_ACCESS", "SPORTS", "Settings"), MPAC_ACCESS("MPAC_ACCESS", "SPORTS", "MPAC"),
	SMART_DASHBOARD_ACCESS("SMART_DASHBOARD_ACCESS", "FINANCE", "Smart Dashboard"),
	HOME_ACCESS("HOME_ACCESS", "FINANCE", "Home");

	private final String code;

	private final String category;

	private final String moduleCode;

}
