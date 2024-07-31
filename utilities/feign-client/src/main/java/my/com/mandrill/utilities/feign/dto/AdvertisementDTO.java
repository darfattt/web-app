package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import my.com.mandrill.utilities.general.constant.EntityName;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertisementDTO implements Serializable {

	private String id;

	private String code;

	private EntityName entityName;

	private String name;

	private String description;

	private Boolean active;

	private String imageEn;

	private String imageCn;

	private String imageMy;

	private String attachmentGroupId;

}
