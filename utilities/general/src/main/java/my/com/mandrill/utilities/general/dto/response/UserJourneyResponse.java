package my.com.mandrill.utilities.general.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.com.mandrill.utilities.general.constant.EntityName;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserJourneyResponse implements Serializable {

	private String id;

	private EntityName entityName;

	private String entityId;

	private JourneyConfigurationResponse journeyStep;

	private Boolean completed;

}
