package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import my.com.mandrill.utilities.general.dto.request.ObjectRequest;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VaultDTO implements Serializable {

	List<AttachmentDTO> attachments;

	private String id;

	private String attachmentGroupId;

	private String name;

	private String className;

	private String type;

	private String description;

	private LocalDate docExpiryDate;

	private ObjectDTO vaultType;

	private Boolean isReminder = false;

	private ObjectRequest linkedEntity;

	private String documentTypeId;

	private String documentTypeName;

	private Boolean isLinked = false;

}
