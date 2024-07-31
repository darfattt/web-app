package my.com.mandrill.utilities.storage.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.storage.configuration.FileStorageProperties;
import my.com.mandrill.utilities.storage.exception.ServiceException;
import my.com.mandrill.utilities.storage.model.InputContentFile;
import my.com.mandrill.utilities.storage.model.OutputContentFile;
import my.com.mandrill.utilities.storage.service.FileManager;
import my.com.mandrill.utilities.storage.util.LocalFileUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Qualifier("awsS3")
public class S3FileManager implements FileManager {

	private final FileStorageProperties fileStorageProperties;

	public S3FileManager(FileStorageProperties fileStorageProperties) {
		this.fileStorageProperties = fileStorageProperties;
	}

	private AWSCredentials getCredential() {
		return new BasicAWSCredentials(this.fileStorageProperties.getAccessKey(),
				fileStorageProperties.getAccessKeySecret());
	}

	@Override
	public void addImage(InputContentFile inputContentFile) throws ServiceException {
		try {
			// TODO watermark
			addFile(inputContentFile);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addFile(InputContentFile inputContentFile) throws ServiceException {
		try {
			// get buckets
			String bucketName = bucketName();
			final AmazonS3 s3 = s3Client();

			// node path
			StringBuilder nodePath = new StringBuilder();
			if (Objects.nonNull(inputContentFile.getPath()) && StringUtils.isNotBlank(inputContentFile.getPath())) {
				nodePath.append(inputContentFile.getPath());
			}

			// file creation
			nodePath.append(LocalFileUtil.formatActualFile(inputContentFile.getKey(), inputContentFile.getFileName()));

			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setUserMetadata(inputContentFile.getMetadata());
			metadata.setContentLength(inputContentFile.getFile().length);
			PutObjectRequest request = new PutObjectRequest(bucketName, nodePath.toString(),
					new ByteArrayInputStream(inputContentFile.getFile()), metadata);

			s3.putObject(request);

		}
		catch (final Exception e) {
			log.error("Error while saving file", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public void addFiles(List<InputContentFile> inputContentFiles) throws ServiceException {
		if (CollectionUtils.isNotEmpty(inputContentFiles)) {
			for (InputContentFile inputFile : inputContentFiles) {
				this.addFile(inputFile);
			}
		}
		log.info("Total {} files added successfully.", inputContentFiles.size());
	}

	@Override
	public OutputContentFile getFile(String path, String key, String fileName) throws ServiceException {
		final AmazonS3 s3 = s3Client();
		S3Object s3Object = s3.getObject(bucketName(), path + LocalFileUtil.formatActualFile(key, fileName));

		OutputContentFile outputContentFile = new OutputContentFile();
		try {
			outputContentFile.setFile(convert(s3Object.getObjectContent()));
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
		final AmazonS3 s3 = s3Client();
		if (s3.doesObjectExist(bucketName(), path + filename)) {
			s3.deleteObject(bucketName(), path + filename);
		}
	}

	private String bucketName() {
		return fileStorageProperties.getBucketName();
	}

	private AmazonS3 s3Client() {
		return AmazonS3ClientBuilder.standard().withRegion(regionName())
				.withCredentials(new AWSStaticCredentialsProvider(getCredential())).build();
	}

	private String regionName() {
		return fileStorageProperties.getRegion();
	}

	public String pathSeparator() {
		return "/";
	}

	public String generateURL(String host, String path, String key) {
		return "";
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

}
