package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentDTO implements Serializable {

	private String id;

	private String name;

	private String type;

	private String referenceNumber;

	private String description;

	@JsonSerialize
	@JsonDeserialize
	private String blobFile;

	@JsonSerialize
	@JsonDeserialize
	private Boolean removeFlag;

	private String url;

	private String subDirectory;

}
