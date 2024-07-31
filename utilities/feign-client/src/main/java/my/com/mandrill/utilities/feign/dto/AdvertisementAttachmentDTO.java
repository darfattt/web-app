package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementAttachmentDTO implements Serializable {

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

}
