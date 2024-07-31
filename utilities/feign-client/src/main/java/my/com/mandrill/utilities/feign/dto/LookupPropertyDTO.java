package my.com.mandrill.utilities.feign.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LookupPropertyDTO implements Serializable {

	private Boolean isText;

	private Boolean isAttachment;

	private String type;

}
