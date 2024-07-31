package my.com.mandrill.utilities.feign.dto;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityDTO implements Serializable {

	private String institutionId;

	private String loginId;

	private String activity;

	private String ipAddress;

	private String deviceName;

	private Instant activityDate;

	private String remarks;

	private String status;

}
