package my.com.mandrill.utilities.general.constant;

import lombok.*;
import my.com.mandrill.utilities.general.exception.ExceptionPredicate;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum DashboardType {

	INSTALL("Install"), UNINSTALL("Uninstall"), SCREEN_TIME("Screen Time"), BANKS("Banks"), LOANS("Loans"),
	PROPERTIES("Properties"), VEHICLES("Vehicles"), UTILITIES("Utilities"), INSURANCES("Insurance"),
	CREDIT_CARD("Credit Card"), TOTAL_OPEN("Total Open"), USER_COUNT("User Count");

	public static final List<DashboardType> MODULE_TIME_SPENT_TYPES = List.of(DashboardType.BANKS, DashboardType.LOANS,
			DashboardType.PROPERTIES, DashboardType.VEHICLES, DashboardType.UTILITIES, DashboardType.INSURANCES,
			DashboardType.CREDIT_CARD);

	private final String description;

	public static DashboardType findByDescription(String description) {
		return Arrays.stream(DashboardType.values()).filter(d -> d.description.equals(description)).findFirst()
				.orElseThrow(ExceptionPredicate.dashboardTypeNotFound(description));
	}

	public static List<DashboardTypeDTO> findAll() {
		return Arrays.stream(DashboardType.values()).map(d -> new DashboardTypeDTO(d.name(), d.description)).toList();
	}

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class DashboardTypeDTO {

		private String code;

		private String description;

	}

}
