package my.com.mandrill.utilities.storage.exception;

public class ServiceException extends RuntimeException {

	public ServiceException(Throwable cause) {
		super(cause.getMessage(), cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
