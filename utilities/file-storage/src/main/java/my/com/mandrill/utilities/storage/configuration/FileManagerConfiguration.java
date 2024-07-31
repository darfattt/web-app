package my.com.mandrill.utilities.storage.configuration;

import my.com.mandrill.utilities.storage.service.FileManager;
import my.com.mandrill.utilities.storage.service.impl.AlibabaCloudFileStorageManager;
import my.com.mandrill.utilities.storage.service.impl.LocalFileManager;
import my.com.mandrill.utilities.storage.service.impl.S3FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileManagerConfiguration {

	@Autowired
	public FileManager oss(FileStorageProperties fileStorageProperties) {
		return new AlibabaCloudFileStorageManager(fileStorageProperties);
	}

	@Autowired
	public FileManager local(FileStorageProperties fileStorageProperties) {
		return new LocalFileManager(fileStorageProperties);
	}

	@Autowired
	public FileManager aws(FileStorageProperties fileStorageProperties) {
		return new S3FileManager(fileStorageProperties);
	}

}