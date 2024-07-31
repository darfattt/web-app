package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstitutionDTO implements Serializable {

	private String id;

	private String shortName;

	private String name;

	private InstitutionDTO parentInstitution;

	private String businessRegNo;

	private String industry;

	private String address;

	private String phone;

	private String fax;

	private String email;

	private String website;

	private Boolean active;

	private String aiMapping;

	private String refNo;

	private String timezone;

	private String country;

	private String state;

	private LocalDate financialYearEnd;

	private String type;

	private String relationship;

	private Integer tier;

}
