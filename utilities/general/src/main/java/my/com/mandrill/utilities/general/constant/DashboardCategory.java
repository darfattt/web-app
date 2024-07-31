package my.com.mandrill.utilities.general.constant;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum DashboardCategory {

	APP_DOWNLOAD("App Download"), APP_TIME_SPENT("App Time Spent"), MODULE_TIME_SPENT("Module Time Spent"),
	MODULE_RECORDS("Module Records"), APP_USER_ACTIVITY("App User Activity"), APP_ACTIVE_USER("App Active User");

	private final String description;

	public static List<DashboardCategoryDTO> findAll() {
		return Arrays.stream(DashboardCategory.values()).map(d -> new DashboardCategoryDTO(d.name(), d.description))
				.toList();
	}

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class DashboardCategoryDTO {

		private String code;

		private String description;

	}

}
