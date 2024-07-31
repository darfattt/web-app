package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AttachmentTypeFileStorageEnum {

	BANK_LOGO(FileMethod.PUBLIC.getMethod(), "bank-logo"), UTILITY_LOGO(FileMethod.PUBLIC.getMethod(), "utility-logo"),
	UTILITY_BILL(FileMethod.PRIVATE.getMethod(), "utility-doc"),
	SALES_AND_PURCHASE_AGREEMENT(FileMethod.PRIVATE.getMethod(), "sales-and-purchase-agreement"),
	CREDIT_CARD(FileMethod.PRIVATE.getMethod(), "credit-card"),
	INSURANCE_LOGO(FileMethod.PUBLIC.getMethod(), "insurance-logo"),
	PROPERTY_DOC(FileMethod.PRIVATE.getMethod(), "property-doc"), BANK_DOC(FileMethod.PRIVATE.getMethod(), "bank-doc"),
	VEHICLE_DOC(FileMethod.PRIVATE.getMethod(), "vehicle-doc"),
	IDENTITY_DOC(FileMethod.PRIVATE.getMethod(), "identity-doc"),
	LEGAL_DOC(FileMethod.PRIVATE.getMethod(), "legal-doc"),
	INSURANCE_DOC(FileMethod.PRIVATE.getMethod(), "insurance-doc"),
	ADVERTISEMENT_IMAGE(FileMethod.PUBLIC.getMethod(), "advertisement-image"),
	WARNING_DOC(FileMethod.PRIVATE.getMethod(), "ESG"), COMPLAINT_DOC(FileMethod.PRIVATE.getMethod(), "ESG"),
	WATER_DOC(FileMethod.PRIVATE.getMethod(), "ESG"), TRAINING_DOC(FileMethod.PRIVATE.getMethod(), "ESG"),
	TRAINING_ADMIN_DOC(FileMethod.PRIVATE.getMethod(), "ESG"),
	INCIDENT_REPORT_DOC(FileMethod.PRIVATE.getMethod(), "ESG"),
	WASTE_MANAGEMENT_DOC(FileMethod.PRIVATE.getMethod(), "ESG"),
	PROFILE_PICTURE_DOC(FileMethod.PRIVATE.getMethod(), "SPORTS"),
	PASSPORT_DOC(FileMethod.PRIVATE.getMethod(), "SPORTS"),
	NPC_NATIONAL_LETTER_DOC(FileMethod.PRIVATE.getMethod(), "SPORTS"),
	COMPETENCY_DETAILS_ATTACHMENT_DOC(FileMethod.PRIVATE.getMethod(), "SPORTS"),
	ATHLETE_DOC(FileMethod.PRIVATE.getMethod(), "SPORTS"), NRIC_DOC(FileMethod.PRIVATE.getMethod(), "SPORTS"),
	SPORTS_DETAIL_SUPPORTING_DOC(FileMethod.PRIVATE.getMethod(), "SPORTS"),
	COMMUNITY_INVESTMENT_SUPPORTING_DOC(FileMethod.PRIVATE.getMethod(), "ESG"),
	ENERGY_MANAGEMENT_SUPPORTING_DOC(FileMethod.PRIVATE.getMethod(), "ESG");

	private final String method;

	private final String basePath;

}
