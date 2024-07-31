package my.com.mandrill.utilities.feign.dto.model;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class UserInterestRecordDTO implements Serializable {

	private String fullName;

	private String email;

	private String phoneNumber;

	private String providerId;

	private String providerName;

	private String issuerCode;

	private String issuerType;

	private String productType;

	private String productName;

	private Instant createdDate;

}
