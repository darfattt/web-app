package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import my.com.mandrill.utilities.general.constant.PurchaseStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleDTO implements Serializable {

	private String id;

	private ObjectDTO vehicleType;

	private String vehicleTypeName;

	private ObjectDTO brand;

	private String brandName;

	private ObjectDTO modelGroup;

	private String modelGroupName;

	private ObjectDTO model;

	private String modelName;

	private ObjectDTO modelVariant;

	private String modelVariantName;

	private ObjectDTO modelYear;

	@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT, pattern = "yyyy")
	private Year modelYearValue;

	private BigDecimal averageMarketValue;

	private PurchaseStatus purchaseStatus;

	private LocalDate roadTaxRenewalDate;

	private String attachmentGroupId;

}
