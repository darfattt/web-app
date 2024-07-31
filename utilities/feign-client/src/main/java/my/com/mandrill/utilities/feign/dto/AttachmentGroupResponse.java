package my.com.mandrill.utilities.feign.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentGroupResponse implements Serializable {

	private String attachmentGroupId;

	private List<AttachmentResponse> attachments;

	private String path;

}
