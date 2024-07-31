package my.com.mandrill.utilities.general.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AttachmentPathEnum {

	WARNING_DOC("Warning", "warning", Boolean.TRUE), COMPLAINT_DOC("Complain", "complaint", Boolean.TRUE),
	WATER_DOC("Water", "water", Boolean.FALSE), TRAINING_DOC("Training", "training", Boolean.TRUE),
	TRAINING_ADMIN_DOC("Training Admin", "training-admin", Boolean.FALSE),
	INCIDENT_REPORT_DOC("Incident Report", "incident-report", Boolean.FALSE),
	WASTE_MANAGEMENT_DOC("Waste Management", "waste-management", Boolean.FALSE),
	PROFILE_PICTURE_DOC("Profile Picture", "profile-picture-doc", Boolean.TRUE),
	PASSPORT_DOC("Passport", "passport-doc", Boolean.TRUE),
	NPC_NATIONAL_LETTER_DOC("NPC National Letter", "npc-national-letter-doc", Boolean.TRUE),
	COMPETENCY_DETAILS_ATTACHMENT_DOC("Competency Details Attachment", "competency-details-attachment-doc",
			Boolean.TRUE),
	ATHLETE_DOC("Athlete", "athlete-doc", Boolean.TRUE), NRIC_DOC("NRIC", "nric-doc", Boolean.TRUE),
	SPORTS_DETAIL_SUPPORTING_DOC("Sports Detail Supporting Document", "sports-detail-supporting-doc", Boolean.TRUE),
	COMMUNITY_INVESTMENT_SUPPORTING_DOC("Community Investment Supporting Document",
			"community-investment-supporting-doc", Boolean.FALSE),
	ENERGY_MANAGEMENT_SUPPORTING_DOC("Energy Management Supporting Document", "energy-management-supporting-doc",
			Boolean.FALSE);

	private final String name;

	private final String path;

	private final Boolean isUserFile;

}
