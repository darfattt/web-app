package my.com.mandrill.utilities.general.service;

public interface GeneralKafkaConsumer {

	void consume(String message);

	void handleError(String message);

}
