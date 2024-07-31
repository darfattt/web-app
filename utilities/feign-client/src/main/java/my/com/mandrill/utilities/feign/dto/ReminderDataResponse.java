package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReminderDataResponse implements Serializable {

	private String id;

	private String label;

	private String name;

	private Boolean isUtility;

	private ReminderDataResponse data;

}
