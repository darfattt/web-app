package my.com.mandrill.utilities.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.com.mandrill.utilities.general.constant.SchedulerType;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDTO {

	private String jobName;

	private String jobGroup;

	private String jobClass;

	private String description;

	private Date nextFireTime;

	private String cronExpression;

	private String triggerState;

	private Map<String, String> data;

	private SchedulerType schedulerType;

	private Instant scheduleTime;

}
