package my.com.mandrill.utilities.feign.client;

import jakarta.validation.Valid;
import my.com.mandrill.utilities.feign.dto.*;
import my.com.mandrill.utilities.feign.dto.model.SignatureChallengeDTO;
import my.com.mandrill.utilities.general.constant.AuthorityEnum;
import my.com.mandrill.utilities.general.util.RequestUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("account-component")
public interface AccountFeignClient {

	@GetMapping("/authenticate")
	List<String> authenticateUser(@RequestParam(name = "accessType") String accessType,
			@RequestParam(name = "loginType") String loginType, @RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "institutionName", required = false) String institutionName);

	@GetMapping("/account")
	UserDTO getAccount();

	@GetMapping("/admin/user")
	UserAdminResponse getCurrentUserAccount();

	@GetMapping("/admin/user/check-authority-pcm-admin")
	Boolean userHasPCMAdminAuthority();

	@GetMapping("/account/ref-no")
	String getRefNoByUsernameAndLoginTypeAndAccessType(@RequestParam(name = "accessType") String accessType,
			@RequestParam(name = "loginType") String loginType, @RequestParam(name = "username") String username,
			@RequestParam(name = "institutionName", required = false) String institutionName);

	@GetMapping("/admin/user/{userId}")
	UserDTO getUserById(@PathVariable(name = "userId") String userId,
			@RequestParam(name = "currentInstitutionId") String currentInstitutionId);

	@GetMapping("/admin/user/refNo/{refNo}")
	UserResponse getUserByRefNo(@PathVariable(name = "refNo") String refNo);

	@GetMapping("/admin/institution/active/{institutionId}")
	InstitutionDTO getActiveInstitutionById(@PathVariable(name = "institutionId") String institutionId);

	@GetMapping("/business-rule/check-imbalance-income-expense")
	Boolean getBusinessRule();

	@GetMapping("/incomes")
	List<IncomeDTO> findByUserAndIncomeType(@RequestParam(required = false, name = "incomeTypeId") String incomeTypeId,
			Sort sort);

	@GetMapping("/expenses")
	List<ExpenseDTO> findByExpenseType(@RequestParam(required = false, name = "expenseTypeId") String expenseTypeId,
			Sort sort);

	@GetMapping("/authenticate/check-password")
	void checkPassword(@RequestParam(name = "password") String password);

	@GetMapping("/account/secret-key")
	String getSecretKey(@RequestHeader(RequestUtil.API_KEY) String apiKey);

	@PutMapping("device-keys/assertion/finish")
	SignatureChallengeDTO assertionFinish(@Valid @RequestBody SignatureChallengeDTO request);

	@GetMapping("/institution/integration/ai-mapping/{code}")
	InstitutionDTO getInstitutionByAiMapping(@PathVariable(name = "code") String code);

	@GetMapping("/institution/integration/ai-mapping")
	List<InstitutionDTO> getAllInstitutionByAiMapping();

	@GetMapping("/email-blacklist")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void validateEmailBlacklist(@RequestParam(name = "email") String email);

	@PostMapping("/registration-key")
	RegistrationKeyDTO createRegistrationKey(@Valid @RequestBody RegistrationKeyDTO registrationKeyDTO);

	@GetMapping("/admin/authority/get-authorities-by-user-id-and-institution-id")
	AuthorityDTO getAuthoritiesByUserIdAndInstitutionId(@RequestParam(name = "userId") String userId,
			@RequestParam(name = "institutionId") String institutionId);

	@GetMapping("/admin/authority/get-authorities-by-user-id")
	List<AuthorityDTO> getAuthoritiesByUserId(@RequestParam(name = "userId") String userId);

	@GetMapping("/admin/user/user-by-email-or-authority")
	List<UserDTO> getUserByEmailOrAuthority(@RequestParam(required = false) List<String> emails,
			@RequestParam(required = false) List<String> authorityIds, @RequestParam String institutionId);

	@GetMapping("/admin/user/email-by-user-ids")
	List<String> getEmailByUserIds(@RequestParam List<String> userIds);

	@GetMapping("/registration-key/validate")
	String validateRegistrationKeyByEmailAndInstitutionName(@RequestParam String email,
			@RequestParam String institutionName);

	@GetMapping("/running-number/generate-incident-report-running-number")
	String generateIncidentReportRunningNo(@RequestParam String institutionId);

	@GetMapping("/running-number/generate-community-investment-running-number")
	String generateCommunityInvestmentRunningNo(@RequestParam String institutionId);

	@GetMapping("/admin/user/user-institution-authority-ids-by-authority")
	List<UserInstitutionAuthorityIdsDTO> getUserInstitutionAuthorityIdsByAuthority(@RequestParam String authority);

	@GetMapping("/admin/user/is-super-admin-account")
	Boolean isSuperAdminAccount(@RequestParam List<String> institutionIds);

	@GetMapping("/account/institutions-authority")
	List<String> getInstitutionIdsAuthorityByAccessTypeLoginTypeAndUserName(
			@RequestParam(name = "accessType") String accessType, @RequestParam(name = "loginType") String loginType,
			@RequestParam(name = "username") String username);

	@GetMapping("/admin/user/authorities/{userId}")
	UserWithAuthoritiesDTO getUserAuthoritiesById(@PathVariable(name = "userId") String userId);

	@GetMapping("/admin/user/user-main-institution/{userId}")
	InstitutionDTO getUserMainInstitution(@PathVariable(name = "userId") String userId);

	@GetMapping("/job-level-type/by-name")
	EmploymentDetailsJobLevelTypeDTO findJobLevelTypeByNameIgnoreCase(@RequestParam String name);

	@GetMapping("/occupation-group/by-name")
	EmploymentDetailsOccupationGroupDTO findOccupationGroupByNameIgnoreCase(@RequestParam String name);

	@GetMapping("/admin/institution/by-name")
	InstitutionDTO findInstitutionByNameIgnoreCase(@RequestParam String name);

	@PostMapping("/admin/user/user-from-excel")
	UserDTO createUserFromExcel(@RequestBody UserDTO userDTO, @RequestParam AuthorityEnum authorityEnum);

	@PostMapping("/employment-details/from-excel")
	EmploymentDetailsDTO createEmploymentDetailsFromExcel(@RequestBody EmploymentDetailsDTO employmentDetailsDTO);

	@GetMapping("/nationality/by-name")
	ObjectDTO findNationalityByNameIgnoreCase(@RequestParam String name);

	@GetMapping("/admin/contract-types/by-name")
	EmploymentDetailsContractTypeDTO findContractTypeByNameIgnoreCase(@RequestParam String name);

	@GetMapping("/admin/employment-types/by-name")
	EmploymentDetailsEmploymentTypeDTO findEmploymentTypeByNameIgnoreCase(@RequestParam String name);

	@PostMapping("/admin/institution/institution-from-excel")
	InstitutionDTO createInstitutionFromExcel(@RequestBody InstitutionDTO institutionDTO);

	@PostMapping("/admin/institution/institution-relationship-from-excel")
	void createInstitutionRelationshipFromExcel(
			@RequestBody InstitutionRelationshipExcelDTO institutionRelationshipExcelDTO);

	@PostMapping("/user-activity")
	void createUserActivity(@RequestBody UserActivityDTO userActivityDTO);

}
