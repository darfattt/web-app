package my.com.mandrill.utilities.storage.service.impl;

import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.storage.configuration.FileStorageProperties;
import my.com.mandrill.utilities.storage.exception.ServiceException;
import my.com.mandrill.utilities.storage.model.InputContentFile;
import my.com.mandrill.utilities.storage.model.OutputContentFile;
import my.com.mandrill.utilities.storage.service.FileManager;
import my.com.mandrill.utilities.storage.util.LocalFileUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Qualifier("local")
public class LocalFileManager implements FileManager {

	private final FileStorageProperties fileStorageProperties;

	public LocalFileManager(FileStorageProperties fileStorageProperties) {
		this.fileStorageProperties = fileStorageProperties;
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
		log.info("Use Local storage...");

		try {

			// base path
			String rootPath = this.buildRootPath();
			Path confDir = Paths.get(rootPath);
			this.createDirectoryIfNorExist(confDir);

			// node path
			StringBuilder nodePath = new StringBuilder();
			nodePath.append(rootPath);
			if (Objects.nonNull(inputContentFile.getPath()) && StringUtils.isNotBlank(inputContentFile.getPath())) {
				nodePath.append(inputContentFile.getPath()).append(File.separator);
			}
			Path nodeDir = Paths.get(nodePath.toString());
			this.createDirectoryIfNorExist(nodeDir);

			// file creation
			nodePath.append(LocalFileUtil.formatActualFile(inputContentFile.getKey(), inputContentFile.getFileName()));

			Path path = Paths.get(nodePath.toString());
			InputStream isFile = new ByteArrayInputStream(inputContentFile.getFile());

			Files.copy(isFile, path, StandardCopyOption.REPLACE_EXISTING);
		}
		catch (Exception e) {
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
		OutputContentFile outputStaticContentData = new OutputContentFile();
		InputStream input = null;
		try {

			// base path
			String rootPath = this.buildRootPath();

			// node path
			StringBuilder nodePath = new StringBuilder();
			nodePath.append(rootPath);
			if (Objects.nonNull(path) && StringUtils.isNotBlank(path)) {
				nodePath.append(path).append(File.separator);
			}

			nodePath.append(LocalFileUtil.formatActualFile(key, fileName));
			Path targetDir = Paths.get(nodePath.toString());

			final byte[] fileBytes = Files.readAllBytes(targetDir);

			if (fileBytes == null) {
				log.warn("file byte is null, no file found");
				return null;
			}

			input = new ByteArrayInputStream(fileBytes);

			final ByteArrayOutputStream output = new ByteArrayOutputStream();
			IOUtils.copy(input, output);

			outputStaticContentData.setFile(output);
			outputStaticContentData.setMimeType(URLConnection.getFileNameMap().getContentTypeFor(fileName));
			outputStaticContentData.setFileName(fileName);

		}
		catch (final Exception e) {
			log.error("Error while fetching file {} ", fileName);
			throw new ServiceException(e);
		}
		return outputStaticContentData;
	}

	@Override
	public OutputContentFile getFile(String path) throws ServiceException {
		OutputContentFile outputStaticContentData = new OutputContentFile();
		InputStream input = null;
		String filename = null;
		try {
			String delimiter = "/";
			String[] tokens = path.split(delimiter);
			filename = tokens[tokens.length];

			// base path
			String rootPath = this.buildRootPath();

			// node path
			String nodePath = rootPath + path;

			Path targetDir = Paths.get(nodePath);

			final byte[] fileBytes = Files.readAllBytes(targetDir);

			if (fileBytes == null) {
				log.warn("file byte is null, no file found");
				return null;
			}

			input = new ByteArrayInputStream(fileBytes);

			final ByteArrayOutputStream output = new ByteArrayOutputStream();
			IOUtils.copy(input, output);

			outputStaticContentData.setFile(output);
			outputStaticContentData.setMimeType(URLConnection.getFileNameMap().getContentTypeFor(filename));
			outputStaticContentData.setFileName(filename);

		}
		catch (final Exception e) {
			log.error("Error while fetching file {} ", filename);
			throw new ServiceException(e);
		}
		return outputStaticContentData;
	}

	@Override
	public void deleteFile(String path, String filename) throws ServiceException {
		try {
			// base path
			String rootPath = this.buildRootPath();

			// node path
			StringBuilder nodePath = new StringBuilder();
			nodePath.append(rootPath);
			if (Objects.nonNull(path) && StringUtils.isNotBlank(path)) {
				nodePath.append(path).append(File.separator);
			}

			nodePath.append(filename);

			Path targetDir = Paths.get(nodePath.toString());

			Files.deleteIfExists(targetDir);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private String buildRootPath() {
		return fileStorageProperties.getRoot() + File.separator;
	}

	private void createDirectoryIfNorExist(Path path) throws IOException {
		if (Files.notExists(path)) {
			Files.createDirectories(path);
		}
	}

	public String pathSeparator() {
		return File.separator;
	}

	public String generateURL(String host, String path, String key) {
		String staticResourceHost = host;
		return staticResourceHost + "/" + path + key;
	}

}
