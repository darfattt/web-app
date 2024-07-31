package my.com.mandrill.utilities.storage.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class FileStorageProperties {

	private String storageType;

	// Local Storage Properties
	private String root;

	// Alibaba Cloud Storage Properties
	private String endpoint;

	private String accessKey;

	private String accessKeySecret;

	// Alibaba Cloud & S3 Storage Properties - Shared
	private String bucketName;

	// S3 Storage Properties
	private String region;

}
