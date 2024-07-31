package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementAttachmentGroupDTO implements Serializable {

	private String attachmentGroupId;

	private String name;

	private String description;

	private String className;

	private String type;

	private String path;

	private List<AdvertisementAttachmentDTO> imageEn;

	private List<AdvertisementAttachmentDTO> imageCn;

	private List<AdvertisementAttachmentDTO> imageMy;

	@JsonIgnore
	private String secretKey;

}
