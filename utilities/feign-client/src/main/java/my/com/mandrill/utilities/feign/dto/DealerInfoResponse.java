package my.com.mandrill.utilities.feign.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DealerInfoResponse {

	private String id;

	private String refNo;

	private String fullName;

	private String email;

	private String phoneCountry;

	private String phoneNumber;

	private String type;

	private String companyName;

	private String addressLine1;

	private String addressLine2;

	private String addressPostal;

	private String addressCity;

	private String addressState;

	private String addressCountry;

	private String subscription;

	private BigDecimal commissionRate;

	private int listing;

	private int photo;

}
