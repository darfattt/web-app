package my.com.mandrill.utilities.feign.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class ProductDocumentResponse {

	private String refNo;

	private String type;

	private Instant date;

	private ProductDTO product;

	private UserDTO seller;

	private UserDTO buyer;

	@Getter
	@Setter
	public static class ProductDTO {

		private String refNo;

		private ModelDTO model;

		private String productionYear;

		private String condition;

		private String scope;

		private String colour;

		private String currency;

		private BigDecimal finalPrice = BigDecimal.ZERO;

		private BigDecimal inspectionCost = BigDecimal.ZERO;

		private BigDecimal commissionCharges = BigDecimal.ZERO;

		private BigDecimal otherCosts = BigDecimal.ZERO;

	}

	@Getter
	@Setter
	public static class ModelDTO {

		private SeriesDTO series;

		private String name;

		private String refNo;

	}

	@Getter
	@Setter
	public static class SeriesDTO {

		private BrandDTO brand;

		private String name;

	}

	@Getter
	@Setter
	public static class BrandDTO {

		private String name;

		private String imageUrl;

	}

	@Getter
	@Setter
	public static class UserDTO {

		private String refNo;

		private String firstName;

		private String lastName;

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

	}

}
