package my.com.mandrill.utilities.storage.service.impl;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.storage.configuration.FileStorageProperties;
import my.com.mandrill.utilities.storage.exception.ServiceException;
import my.com.mandrill.utilities.storage.model.InputContentFile;
import my.com.mandrill.utilities.storage.model.OutputContentFile;
import my.com.mandrill.utilities.storage.service.FileManager;
import my.com.mandrill.utilities.storage.util.LocalFileUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Qualifier("alibabaCloudStorage")
public class AlibabaCloudFileStorageManager implements FileManager {

	private final FileStorageProperties fileStorageProperties;

	public AlibabaCloudFileStorageManager(FileStorageProperties fileStorageProperties) {
		this.fileStorageProperties = fileStorageProperties;
	}

	private OSS getOssClientInstance() {
		return new OSSClientBuilder().build(fileStorageProperties.getEndpoint(), fileStorageProperties.getAccessKey(),
				fileStorageProperties.getAccessKeySecret());
	}

	@Override
	public void addImage(InputContentFile inputContentFile) throws ServiceException {
		try {
			addFile(inputContentFile);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addFile(InputContentFile inputContentFile) throws ServiceException {
		OSS ossClient = getOssClientInstance();

		ossClient.putObject(fileStorageProperties.getBucketName(),
				inputContentFile.getPath()
						+ LocalFileUtil.formatActualFile(inputContentFile.getKey(), inputContentFile.getFileName()),
				new ByteArrayInputStream(inputContentFile.getFile()));
		ossClient.shutdown();
	}

	@Override
	public void addFiles(List<InputContentFile> inputContentFiles) throws ServiceException {
		// TODO
	}

	@Override
	public OutputContentFile getFile(String path, String key, String fileName) throws ServiceException {
		OSS ossClient = getOssClientInstance();

		OSSObject ossObject = ossClient.getObject(fileStorageProperties.getBucketName(),
				path + LocalFileUtil.formatActualFile(key, fileName));
		OutputContentFile outputContentFile = new OutputContentFile();
		try {
			outputContentFile.setFile(convert(ossObject.getObjectContent()));
		}
		catch (IOException e) {
			throw new ServiceException(e);
		}
		outputContentFile.setFileName(fileName);
		outputContentFile.setPath(path);
		return outputContentFile;
	}

	@Override
	public OutputContentFile getFile(String path) throws ServiceException {
		return null;
	}

	@Override
	public void deleteFile(String path, String filename) throws ServiceException {
		OSS ossClient = getOssClientInstance();
		if (ossClient.doesObjectExist(fileStorageProperties.getBucketName(), path + filename)) {
			ossClient.deleteObject(fileStorageProperties.getBucketName(), path + filename);
		}
	}

	private ByteArrayOutputStream convert(InputStream inputStream) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		byte[] buffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			byteArrayOutputStream.write(buffer, 0, bytesRead);
		}

		return byteArrayOutputStream;
	}

	public String pathSeparator() {
		return "/";
	}

	@Override
	public String generateURL(String host, String path, String key) {
		OSS ossClient = getOssClientInstance();

		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.HOUR, 1);

		URL url = ossClient.generatePresignedUrl(fileStorageProperties.getBucketName(), path + key, cal.getTime(),
				HttpMethod.GET);

		return url.toString();
	}

}
