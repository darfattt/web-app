package my.com.mandrill.utilities.general.dto.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@ToString
public abstract class EventEntityDTO implements Serializable {

	private Instant createdDate;

	private String createdBy;

	private Instant lastModifiedDate;

	private String lastModifiedBy;

}
