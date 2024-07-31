package my.com.mandrill.utilities.general.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class MessageQueueProperties {

	private String type;

	private Boolean enabled;

	private String region;

	private String accessKey;

	private String accessKeySecret;

	private String url;

}
