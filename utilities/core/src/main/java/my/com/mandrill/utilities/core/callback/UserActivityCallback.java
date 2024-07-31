package my.com.mandrill.utilities.core.callback;

public interface UserActivityCallback {

	void saveUserActivity(String institutionId, String activity, String ipAddress, String deviceName, String remarks,
			String status);

}
