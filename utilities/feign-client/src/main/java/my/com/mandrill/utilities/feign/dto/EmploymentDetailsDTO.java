package my.com.mandrill.utilities.feign.dto;

import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentDetailsDTO {

	private String id;

	private EmploymentDetailsEmploymentTypeDTO employmentType;

	private String designation;

	private EmploymentDetailsContractTypeDTO contractType;

	private EmploymentDetailsJobLevelTypeDTO jobLevelType;

	private LocalDate employmentStartDate;

	private LocalDate employmentEndDate;

	private Boolean active;

	private EmploymentDetailsOccupationGroupDTO occupationGroup;

	private UserDTO user;

	private InstitutionDTO institution;

}
