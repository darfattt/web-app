package my.com.mandrill.utilities.feign.dto;

import lombok.*;
import my.com.mandrill.utilities.general.constant.InstitutionRelationshipLinkageEnum;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionRelationshipExcelDTO {

	private InstitutionDTO parentInstitution;

	private List<InstitutionDTO> childInstitutions;

	private InstitutionRelationshipLinkageEnum institutionRelationshipLinkageEnum;

}
