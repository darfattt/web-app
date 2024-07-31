package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VaultTypeEnum {

	MY_PROPERTY("MY_PROPERTY", "My Property", EntityName.PROPERTY, DocumentTypeEnum.PROPERTY_TYPE),
	MY_IDENTITY("MY_IDENTITY", "My Identity", EntityName.USER, DocumentTypeEnum.IDENTITY_TYPE),
	MY_VEHICLE("MY_VEHICLE", "My Vehicle", EntityName.VEHICLE, DocumentTypeEnum.VEHICLE_TYPE),
	MY_UTILITY("MY_UTILITY", "My Utility", EntityName.UTILITY, DocumentTypeEnum.UTILITY_TYPE),
	MY_FINANCE("MY_FINANCE", "My Finance", EntityName.BANK, DocumentTypeEnum.FINANCE_TYPE),
	MY_LEGAL_DOCS("MY_LEGAL_DOCS", "My Legal Docs", EntityName.LEGAL, DocumentTypeEnum.LEGAL_TYPE),
	MY_INSURANCE("MY_INSURANCE", "My Insurance", EntityName.INSURANCE, DocumentTypeEnum.INSURANCE_TYPE);

	private final String name;

	private final String description;

	private final EntityName entityName;

	private final DocumentTypeEnum documentType;

}
