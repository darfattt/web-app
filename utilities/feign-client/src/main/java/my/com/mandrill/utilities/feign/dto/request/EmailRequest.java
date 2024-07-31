package my.com.mandrill.utilities.feign.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.com.mandrill.utilities.feign.dto.model.Attachment;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest implements Serializable {

	@NotNull
	private List<String> to;

	private List<String> cc;

	private List<String> bcc;

	private Set<Attachment> attachments;

	@Builder.Default
	private Map<String, Object> templateVariable = new HashMap<>();

	@NotBlank
	private String from;

}
