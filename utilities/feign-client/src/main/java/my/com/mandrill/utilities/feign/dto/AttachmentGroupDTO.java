package my.com.mandrill.utilities.feign.dto;

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
public class AttachmentGroupDTO implements Serializable {

	List<AttachmentDTO> attachments;

	private String attachmentGroupId;

	private String attachmentGroupParentId;

	private String name;

	private String description;

	private String className;

	private String type;

	private String path;

	private ObjectDTO vaultType;

	private Boolean isReminder;

	private String secretKey;

	private String userId;

	private String institutionId;

	private Boolean isUserFile;

}
