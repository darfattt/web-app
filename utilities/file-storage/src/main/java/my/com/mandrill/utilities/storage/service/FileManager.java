package my.com.mandrill.utilities.storage.service;

import my.com.mandrill.utilities.storage.exception.ServiceException;
import my.com.mandrill.utilities.storage.model.InputContentFile;
import my.com.mandrill.utilities.storage.model.OutputContentFile;

import java.util.List;

public interface FileManager {

	void addImage(InputContentFile inputContentFile) throws ServiceException;

	void addFile(InputContentFile inputContentFile) throws ServiceException;

	void addFiles(List<InputContentFile> inputContentFiles) throws ServiceException;

	OutputContentFile getFile(String path, String key, String fileName) throws ServiceException;

	OutputContentFile getFile(String path) throws ServiceException;

	void deleteFile(String path, String name) throws ServiceException;

	String pathSeparator();

	String generateURL(String host, String path, String key);

}
