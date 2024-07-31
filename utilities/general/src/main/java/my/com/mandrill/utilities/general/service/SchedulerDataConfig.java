package my.com.mandrill.utilities.general.service;

public interface SchedulerDataConfig {

	String TWELVE_ONE_MINUTE_AM_KL_CRON = "0 1 16 1/1 * ? *";

	String ELEVEN_FIFTY_NINE_MINUTE_PM_KL_CRON = "0 59 15 1/1 * ? *";

	// added for testing purpose
	String EVERY_FIVE_SECOND = "*/5 * * ? * * *";

	String EVERY_DAY_MIDNIGHT_TWELVE_AM = "0 0 0 * * ?";

	String EVERY_FIRST_DAY_OF_A_YEAR = "0 0 0 1 JAN ?";

	String EVERY_FIRST_DAY_OF_A_MONTH = "0 0 0 1 * ?";

	String getDestination();

	String getJobGroup();

	String getJobName();

	String getCronExpression();

}
