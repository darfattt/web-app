package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SubModuleAccessEnum {

	// TODO need to standardized category naming method and need to sync with db
	USER_MANAGEMENT_ACCESS("USER_MANAGEMENT_ACCESS", "User Management", "User Management"),
	DISCLOSURE_REQUEST_ACCESS("DISCLOSURE_REQUEST_ACCESS", "Disclosure Request", "Disclosure Request"),
	EMPLOYEE_INFO_ACCESS("EMPLOYEE_INFO_ACCESS", "Employee Info", "Employee Info"),
	INCIDENT_REPORT_ACCESS("INCIDENT_REPORT_ACCESS", "Incident Report", "Incident Report"),
	TRAINING_HOURS_ACCESS("TRAINING_HOURS_ACCESS", "Training Hours", "Training Hours"),
	WATER_ACCESS("WATER_ACCESS", "Water", "Water"),
	WASTE_MANAGEMENT_ACCESS("WASTE_MANAGEMENT_ACCESS", "Waste Management", "Waste Management"),
	APP_ADMIN_ORGANISATION_ACCESS("APP_ADMIN_ORGANISATION_ACCESS", "App Admin Organisation", "Organisation"),
	APP_ADMIN_PERMISSION_ACCESS("APP_ADMIN_PERMISSION_ACCESS", "App Admin Permission", "Permission"),
	APP_ADMIN_ROLE_ACCESS("APP_ADMIN_ROLE_ACCESS", "App Admin Role", "Role Report"),
	APP_ADMIN_USER_ACCESS("APP_ADMIN_USER_ACCESS", "App Admin User", "User"),
	CLASSIFICATION_ACCESS("CLASSIFICATION_ACCESS", "Classification", "Classification"),
	SPORTS_ASSOCIATION_ACCESS("SPORTS_ASSOCIATION_ACCESS", "SPORTS", "Sports Association"),
	PROFILE_ACCESS("PROFILE_ACCESS", "SPORTS", "Profile"),
	PARA_SPORTS_ACCESS("PARA_SPORTS_ACCESS", "SPORTS", "Para-Sports"),
	CLASSIFIER_ACCESS("CLASSIFIER_ACCESS", "SPORTS", "Classifier"),
	PERSONAL_INFO_ACCESS("PERSONAL_INFO_ACCESS", "SPORTS", "Personal Info"),
	PERFORMANCE_ACCESS("PERFORMANCE_ACCESS", "Performance", "Performance"),
	CARBON_EMISSION_ACCESS("PERFORMANCE_ACCESS", "Carbon Emission", "Carbon Emission"),
	EMISSION_MANAGEMENT_ACCESS("EMISSION_MANAGEMENT_ACCESS", "Emission Management", "Emission Management"),
	MANAGEMENT_REPORT_ACCESS("MANAGEMENT_REPORT_ACCESS", "FINANCE", "Management Report"),
	ENERGY_MANAGEMENT_ACCESS("ENERGY_MANAGEMENT_ACCESS", "Energy Management", "Energy management"),
	APP_ADMIN_AUDIT_LOG_ACCESS("APP_ADMIN_AUDIT_LOG_ACCESS", "App Admin Audit Log", "Audit Log");

	private final String code;

	private final String category;

	private final String subModuleCode;

}
