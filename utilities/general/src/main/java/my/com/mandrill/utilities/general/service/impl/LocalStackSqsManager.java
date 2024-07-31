package my.com.mandrill.utilities.general.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.general.configuration.MessageQueueProperties;
import my.com.mandrill.utilities.general.service.QueueManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Qualifier("localstack")
@RequiredArgsConstructor
@ConditionalOnBean(MessageQueueProperties.class)
public class LocalStackSqsManager implements QueueManager {

	private final MessageQueueProperties messageQueueProperties;

	private final ObjectMapper objectMapper;

	@Override
	public void publishMessage(Object messageBody) {
		final AmazonSQS sqs = sqsClient();

		try {
			sqs.sendMessage(messageQueueProperties.getUrl(), objectMapper.writeValueAsString(messageBody));
		}
		catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private AWSCredentials getCredential() {
		return new BasicAWSCredentials(this.messageQueueProperties.getAccessKey(),
				messageQueueProperties.getAccessKeySecret());
	}

	private AmazonSQS sqsClient() {
		return AmazonSQSClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:4566", null))
				.withCredentials(new AWSStaticCredentialsProvider(getCredential())).build();
	}

}
