package my.com.mandrill.utilities.feign.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import my.com.mandrill.utilities.general.constant.Language;
import my.com.mandrill.utilities.general.constant.LoginTypeEnum;
import my.com.mandrill.utilities.general.dto.model.EventEntityDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO extends EventEntityDTO implements Serializable {

	private String id;

	private String refNo;

	private String username;

	private String provider;

	private String fullName;

	private String email;

	private Boolean emailVerified;

	private String phoneCountry;

	private String phoneNumber;

	private Boolean phoneVerified;

	private Language langKey;

	private Boolean active;

	private Integer loginFailAttempt;

	private List<AuthorityDTO> authorities;

	private List<InstitutionDTO> institutions;

	private LoginTypeEnum loginType;

	private String nric;

	private String address1;

	private String address2;

	private String address3;

	private String postcode;

	private ObjectDTO nationality;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dob;

	private String gender;

	private String maritalStatus;

	private String ethnicity;

	private String religion;

	private ObjectDTO currency;

	private BigDecimal epfContribution;

	private Boolean socso;

	private Boolean eis;

	private ObjectDTO educationLevel;

	private ObjectDTO employmentType;

	private ObjectDTO occupationGroup;

	private ObjectDTO businessNature;

	private String selfEmployedName;

	private List<ObjectDTO> interests;

	private List<ObjectDTO> financialGoals;

	private List<IncomeDTO> incomes;

	private List<ExpenseDTO> expenses;

	private List<BankDTO> banks;

	// for reminder
	private List<BankDetailDTO> bankDetails;

	private CountryDTO country;

	private StateDTO state;

	// for reminder
	private VehicleDTO vehicle;

	// for reminder
	private InsuranceDTO insurance;

	// for reminder
	private LoanDTO loan;

	private String attachmentGroupId;

	private InstitutionDTO mainInstitution;

	private String staffId;

	private LocalDate dateOfBirth;

	public String getLoginId() {
		return (this.getEmail() != null && !this.getEmail().isEmpty()) ? this.getEmail() : this.getStaffId();
	}

}
