package my.com.mandrill.utilities.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.com.mandrill.utilities.general.constant.EntityName;
import my.com.mandrill.utilities.general.constant.FlowType;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserJourneyRequest implements Serializable {

	private String id;

	private EntityName entityName;

	private String entityId;

	private List<InterestTypeDTO> interestTypes;

	private Boolean manualCompleteUserJourney;

	private FlowType flowType;

}
