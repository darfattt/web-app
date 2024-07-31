package my.com.mandrill.utilities.general.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.general.configuration.SecretManagerProperties;
import my.com.mandrill.utilities.general.service.SecretManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Qualifier("aws")
@ConditionalOnBean(SecretManagerProperties.class)
public class AWSSecretManager implements SecretManager {

	private final SecretManagerProperties secretManagerProperties;

	public AWSSecretManager(SecretManagerProperties secretManagerProperties) {
		this.secretManagerProperties = secretManagerProperties;
	}

	@Override
	public String getSecret(String key) {
		final AWSSecretsManager sm = secretManagerClient();

		String secretName = new StringBuffer().append(secretManagerProperties.getPrefix()).append(key).toString();

		GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest();
		getSecretValueRequest.setSecretId(secretName);

		GetSecretValueResult getSecretValueResult = sm.getSecretValue(getSecretValueRequest);

		return getSecretValueResult.getSecretString();
	}

	private AWSCredentials getCredential() {
		return new BasicAWSCredentials(this.secretManagerProperties.getAccessKey(),
				secretManagerProperties.getAccessKeySecret());
	}

	private String getRegion() {
		return secretManagerProperties.getRegion();
	}

	private AWSSecretsManager secretManagerClient() {
		return AWSSecretsManagerClientBuilder.standard().withRegion(getRegion())
				.withCredentials(new AWSStaticCredentialsProvider(getCredential())).build();
	}

}
