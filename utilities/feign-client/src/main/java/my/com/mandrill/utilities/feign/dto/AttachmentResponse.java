package my.com.mandrill.utilities.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentResponse implements Serializable {

	private String id;

	private String name;

	private String type;

	private String referenceNumber;

	private String description;

	private String url;

	private Instant createdDate;

}
