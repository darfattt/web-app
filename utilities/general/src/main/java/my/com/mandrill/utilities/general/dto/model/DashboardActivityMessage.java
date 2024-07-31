package my.com.mandrill.utilities.general.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.com.mandrill.utilities.general.constant.DashboardCategory;
import my.com.mandrill.utilities.general.constant.DashboardType;

import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardActivityMessage implements Serializable {

	private DashboardCategory category;

	private DashboardType type;

	private Long value;

	private Instant createdDate;

	private String userId;

}
