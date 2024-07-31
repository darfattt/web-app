package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VaultResponse implements Serializable {

	private String id;

	private String name;

	private String description;

	private ObjectDTO documentType;

	private String documentTypeName;

	private LocalDate docExpiryDate;

	private List<VaultAttachmentResponse> attachments;

}
