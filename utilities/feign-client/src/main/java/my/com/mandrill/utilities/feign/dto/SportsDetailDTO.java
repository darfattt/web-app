package my.com.mandrill.utilities.feign.dto;

import lombok.*;
import my.com.mandrill.utilities.general.dto.model.EventEntityDTO;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportsDetailDTO extends EventEntityDTO implements Serializable {

	private String id;

	private String userId;

	private ParaSportsDTO paraSports;

	private String firstClass;

	private String secondClass;

	private String thirdClass;

	private String classificationSheet1AttachmentGroupId;

	private String classificationSheet2AttachmentGroupId;

	private String classificationSheet3AttachmentGroupId;

	private String classificationSheet4AttachmentGroupId;

	private String medicalReviewRequestAttachmentGroupId;

	private String mrrSupportingDocumentAttachmentGroupId;

	private String medicalReviewOutcomeAttachmentGroupId;

	private String protestSheetAttachmentGroupId;

	private String medicalDiagnosticFormAttachmentGroupId;

	private String tsalForm2AttachmentGroupId;

	private String supportingMedicalDocumentAttachmentGroupId;

	private String institutionId;

}
