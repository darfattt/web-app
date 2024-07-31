package my.com.mandrill.utilities.general.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SecretManagerProperties {

	private String storeType;

	private Boolean enabled;

	private String accessKey;

	private String accessKeySecret;

	private String region;

	private String prefix;

}
