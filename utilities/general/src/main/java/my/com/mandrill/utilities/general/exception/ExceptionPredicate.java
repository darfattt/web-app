package my.com.mandrill.utilities.general.exception;

import jakarta.persistence.EntityNotFoundException;
import my.com.mandrill.utilities.general.constant.LoginTypeEnum;
import org.springframework.security.access.AccessDeniedException;

import java.util.function.Supplier;

public final class ExceptionPredicate {

	private static final String NOT_SUPPORTED_MESSAGE_FORMAT = "%s not supported";

	private ExceptionPredicate() {
		throw new IllegalStateException("Utility class");
	}

	public static Supplier<EntityNotFoundException> institutionNotFound(String institutionId) {
		return () -> new EntityNotFoundException(String.format("Institution with id %s was not found", institutionId));
	}

	public static Supplier<EntityNotFoundException> institutionNotFoundByTier(Integer tier) {
		return () -> new EntityNotFoundException(String.format("Institution with tier %s was not found", tier));
	}

	public static Supplier<EntityNotFoundException> currentUserAndInstitutionNotMatch(String username,
			String institutionId) {
		return () -> new EntityNotFoundException(
				String.format("Institution with id %s and username %s was not match", institutionId, username));
	}

	public static Supplier<EntityNotFoundException> sysConfigByIdNotFound(String systemConfigurationId) {
		return () -> new EntityNotFoundException(
				String.format("System Configuration with id=%s Not Found", systemConfigurationId));
	}

	public static Supplier<EntityNotFoundException> sysConfigByInstitutionIdAndCodeNotFound(String institutionId,
			String code) {
		return () -> new EntityNotFoundException(
				String.format("System Configuration with institutionId=%s and code=%s Not Found", institutionId, code));
	}

	public static Supplier<EntityNotFoundException> permissionByIdNotFound(String permissionId) {
		return () -> new EntityNotFoundException(String.format("Permission with id=%s Not Found", permissionId));
	}

	public static Supplier<EntityNotFoundException> authorityByIdNotFound(String authorityId) {
		return () -> new EntityNotFoundException(String.format("Authority with id=%s Not Found", authorityId));
	}

	public static Supplier<EntityNotFoundException> authorityByIdAndInstitutionIdNotFound(String authorityId,
			String institutionId) {
		return () -> new EntityNotFoundException(
				String.format("Authority with id=%s and Institution with id=%s Not Found", authorityId, institutionId));
	}

	public static Supplier<EntityNotFoundException> businessNatureNotFound(String businessNatureId) {
		return () -> new EntityNotFoundException(
				String.format("BusinessNature with id=%s Not Found", businessNatureId));
	}

	public static Supplier<EntityNotFoundException> countryNotFound(String countryId) {
		return () -> new EntityNotFoundException(String.format("Country with id=%s Not Found", countryId));
	}

	public static Supplier<EntityNotFoundException> currencyNotFound(String currencyId) {
		return () -> new EntityNotFoundException(String.format("Currency with id=%s Not Found", currencyId));
	}

	public static Supplier<EntityNotFoundException> educationLevelNotFound(String educationLevelId) {
		return () -> new EntityNotFoundException(
				String.format("EducationLevel with id=%s Not Found", educationLevelId));
	}

	public static Supplier<EntityNotFoundException> employmentTypeNotFound(String employmentTypeId) {
		return () -> new EntityNotFoundException(
				String.format("EmploymentType with id=%s Not Found", employmentTypeId));
	}

	public static Supplier<EntityNotFoundException> nationalityNotFound(String nationalityId) {
		return () -> new EntityNotFoundException(String.format("Nationality with id=%s Not Found", nationalityId));
	}

	public static Supplier<EntityNotFoundException> occupationGroupNotFound(String occupationGroupId) {
		return () -> new EntityNotFoundException(
				String.format("OccupationGroup with id=%s Not Found", occupationGroupId));
	}

	public static Supplier<EntityNotFoundException> stateNotFound(String stateId) {
		return () -> new EntityNotFoundException(String.format("State with id=%s Not Found", stateId));
	}

	public static Supplier<EntityNotFoundException> interestNotFound(String interestId) {
		return () -> new EntityNotFoundException(String.format("Interest with id=%s Not Found", interestId));
	}

	public static Supplier<EntityNotFoundException> financialGoalNotFound(String financialGoalId) {
		return () -> new EntityNotFoundException(String.format("Financial Goal with id=%s Not Found", financialGoalId));
	}

	public static Supplier<EntityNotFoundException> domainNotFound(String domainId) {
		return () -> new EntityNotFoundException(String.format("Domain with id=%s Not Found", domainId));
	}

	public static Supplier<EntityNotFoundException> domainNotFound(String code, String name) {
		return () -> new EntityNotFoundException(
				String.format("Domain with code=%s and name=%s Not Found", code, name));
	}

	public static Supplier<EntityNotFoundException> userNotFound(String username, LoginTypeEnum loginType) {
		return () -> new EntityNotFoundException(
				String.format("User with username=%s and loginType=%s Not Found", username, loginType));
	}

	public static Supplier<EntityNotFoundException> userNotFoundByRefNo(String refNo) {
		return () -> new EntityNotFoundException(
				String.format("User with username=%s and loginType=%s Not Found", refNo));
	}

	public static Supplier<EntityNotFoundException> emailNotFound(String email, LoginTypeEnum loginType) {
		return () -> new EntityNotFoundException(
				String.format("User with email=%s and loginType=%s Not Found", email, loginType));
	}

	public static Supplier<EntityNotFoundException> incomeTypeNotFound(String id) {
		return () -> new EntityNotFoundException(String.format("Income Type with id=%s Not Found", id));
	}

	public static Supplier<EntityNotFoundException> sysConfigNotFound(String code) {
		return () -> new EntityNotFoundException(
				String.format("System Configuration with code: %s was not found", code));
	}

	public static Supplier<EntityNotFoundException> segmentNotFound(String segmentId) {
		return () -> new EntityNotFoundException(String.format("Segment with id=%s Not Found", segmentId));
	}

	public static Supplier<AccessDeniedException> cantAccessUser() {
		return () -> new AccessDeniedException("System can't load user data");
	}

	public static Supplier<EntityNotFoundException> expenseTypeNotFound(String id) {
		return () -> new EntityNotFoundException(String.format("Expense Type with id=%s Not Found", id));
	}

	public static Supplier<EntityNotFoundException> authorityNotFoundByName(String name) {
		return () -> new EntityNotFoundException(String.format("Authority with name=%s Not Found", name));

	}

	public static Supplier<EntityNotFoundException> userNotFoundByUsernameIgnoreCaseAndLoginType(String username,
			LoginTypeEnum loginType) {
		return () -> new EntityNotFoundException(
				String.format("User with username=%s and loginType=%s Not Found", username, loginType));
	}

	public static Supplier<EntityNotFoundException> bankNotFound(String id) {
		return () -> new EntityNotFoundException(String.format("Bank with id=%s Not Found", id));
	}

	public static Supplier<EntityNotFoundException> bankDetailNotFound(String id) {
		return () -> new EntityNotFoundException(String.format("Bank Detail with id=%s Not Found", id));
	}

	public static Supplier<EntityNotFoundException> bankListNotFound(String id) {
		return () -> new EntityNotFoundException(String.format("Bank List with id=%s Not Found", id));
	}

	public static Supplier<EntityNotFoundException> bankListAiMappingNotFound(String aiMapping) {
		return () -> new EntityNotFoundException(String.format("Provider with name: %s not found", aiMapping));
	}

	public static Supplier<EntityNotFoundException> bankListInstitutionMappingNotFound(String aiMapping) {
		return () -> new EntityNotFoundException(String.format("Provider with institution: %s not found", aiMapping));
	}

	public static Supplier<EntityNotFoundException> bankAccountTypeNotFound(String id) {
		return () -> new EntityNotFoundException(String.format("Bank Account Type with id=%s Not Found", id));
	}

	public static Supplier<EntityNotFoundException> propertyTypeNotFoundById(String id) {
		return () -> new EntityNotFoundException(String.format("Property Type with Id=%s Not Found", id));
	}

	public static Supplier<EntityNotFoundException> propertySubTypeNotFoundById(String id) {
		return () -> new EntityNotFoundException(String.format("Property Sub Type with Id=%s Not Found", id));
	}

	public static Supplier<EntityNotFoundException> propertyNotFoundById(String id) {
		return () -> new EntityNotFoundException(String.format("Property with Id=%s Not Found", id));
	}

	public static Supplier<GlobalNotSupportedException> notSupportedByEntityName(String entityName) {
		return () -> new GlobalNotSupportedException(String.format(NOT_SUPPORTED_MESSAGE_FORMAT, entityName));
	}

	public static Supplier<EntityNotFoundException> utilityNotFoundById(String id) {
		return () -> new EntityNotFoundException(String.format("Utility with Id=%s Not Found", id));
	}

	public static Supplier<EntityNotFoundException> bankDetailNotFoundByAttachmentGroupId(String id) {
		return () -> new EntityNotFoundException(
				String.format("Bank Detail with attachment group id=%s Not Found", id));
	}

	public static Supplier<EntityNotFoundException> dashboardTypeNotFound(String description) {
		return () -> new EntityNotFoundException(
				"Dashboard Type with description %s was not found".formatted(description));
	}

	public static Supplier<EntityNotFoundException> institutionNotFoundByAiMapping(String aiMapping) {
		return () -> new EntityNotFoundException(String.format("Institution with code %s was not found", aiMapping));
	}

	public static Supplier<EntityNotFoundException> userNotFoundById(String id) {
		return () -> new EntityNotFoundException(String.format("User with id %s was not found", id));
	}

	public static Supplier<EntityNotFoundException> registrationKeyNotFoundByRegisterKey(String registerKey) {
		return () -> new EntityNotFoundException(
				String.format("Registration Key with register key %s Not Found", registerKey));

	}

	public static Supplier<EntityNotFoundException> contractTypeNotFound(String contractTypeId) {
		return () -> new EntityNotFoundException(String.format("Contract Type with id=%s Not Found", contractTypeId));
	}

	public static Supplier<EntityNotFoundException> moduleNotFound(String moduleId) {
		return () -> new EntityNotFoundException(String.format("Module with id=%s Not Found", moduleId));
	}

	public static Supplier<EntityNotFoundException> jobLevelTypeNotFoundById(String jobLevelTypeId) {
		return () -> new EntityNotFoundException(String.format("Job level type with id %s not found", jobLevelTypeId));
	}

	public static Supplier<EntityNotFoundException> vehicleTypeNotFoundById(String vehicleTypeId) {
		return () -> new EntityNotFoundException(String.format("Vehicle type with id %s not found", vehicleTypeId));
	}

	public static Supplier<EntityNotFoundException> vehicleClassNotFoundById(String vehicleClassId) {
		return () -> new EntityNotFoundException(String.format("Vehicle class with id %s not found", vehicleClassId));
	}

	public static Supplier<EntityNotFoundException> vehicleClassTypeNotFoundById(String vehicleClassTypeId) {
		return () -> new EntityNotFoundException(
				String.format("Vehicle class type with id %s not found", vehicleClassTypeId));
	}

	public static Supplier<EntityNotFoundException> trainingNotFound(String trainingId) {
		return () -> new EntityNotFoundException(String.format("Training with id=%s Not Found", trainingId));
	}

	public static Supplier<EntityNotFoundException> fuelTypeNotFound(String fuelTypeId) {
		return () -> new EntityNotFoundException(String.format("Fuel Type with ID: %s Not Found", fuelTypeId));
	}

	public static Supplier<EntityNotFoundException> complaintNotFound(String complaintId) {
		return () -> new EntityNotFoundException(String.format("Complaint with id=%s Not Found", complaintId));
	}

	public static Supplier<EntityNotFoundException> warningNotFound(String warningId) {
		return () -> new EntityNotFoundException(String.format("Warning with id=%s Not Found", warningId));
	}

	public static Supplier<? extends RuntimeException> ghgConversionFactorNotFound(String ghgConversionFactorId) {
		return () -> new EntityNotFoundException(
				String.format("GhgConversionFactor with id=%s Not Found", ghgConversionFactorId));
	}

	public static Supplier<? extends RuntimeException> carbonEmissionNotFound(String carbonEmissionId) {
		return () -> new EntityNotFoundException(
				String.format("CarbonEmission with id=%s Not Found", carbonEmissionId));
	}

	public static Supplier<EntityNotFoundException> employmentDetailsNotFoundById(String employmentDetailsId) {
		return () -> new EntityNotFoundException(
				String.format("Employment details with id %s not found", employmentDetailsId));
	}

	public static Supplier<? extends RuntimeException> userInstitutionAuthorityNotFound(String userId,
			String institutionId) {
		return () -> new EntityNotFoundException(String.format(
				"UserInstitutionAuthority with userId=%s and institutionId=%s Not Found", userId, institutionId));
	}

	public static Supplier<? extends RuntimeException> waterNotFound(String waterId) {
		return () -> new EntityNotFoundException(String.format("Water with id=%s Not Found", waterId));
	}

	public static Supplier<EntityNotFoundException> incidentReportNotFound(String incidentReportId) {
		return () -> new EntityNotFoundException(
				String.format("IncidentReport with id=%s Not Found", incidentReportId));
	}

	public static Supplier<EntityNotFoundException> registrationKeyNotFoundByRecipientEmailAndInstitutionName(
			String email, String institutionName) {
		return () -> new EntityNotFoundException(String.format(
				"Registration Key with Recipient Email %s and Institution Name %s Not Found", email, institutionName));

	}

	public static Supplier<? extends RuntimeException> wasteManagementNotFound(String wasteManagementId) {
		return () -> new EntityNotFoundException(
				String.format("Waste Management with id=%s Not Found", wasteManagementId));
	}

	public static Supplier<EntityNotFoundException> authorityNotFoundByNameAndComponent(String name, String component) {
		return () -> new EntityNotFoundException(
				String.format("Authority with name=%s and component=%s Not Found", name, component));

	}

	public static Supplier<EntityNotFoundException> sportsDetailsNotFound(String sportsDetailsId) {
		return () -> new EntityNotFoundException(String.format("Sports Details with id=%s Not Found", sportsDetailsId));
	}

	public static Supplier<EntityNotFoundException> sportsDetailsNotFound(String userId, String institutionId) {
		return () -> new EntityNotFoundException(String
				.format("Sports Details with userId=%s and institutionId=%s " + "Not Found", userId, institutionId));
	}

	public static Supplier<EntityNotFoundException> communityInvestmentNotFound(String communityInvestmentId) {
		return () -> new EntityNotFoundException(
				String.format("Community Investment with id=%s Not Found", communityInvestmentId));
	}

	public static Supplier<EntityNotFoundException> energyManagementNotFound(String energyManagementId) {
		return () -> new EntityNotFoundException(
				String.format("Energy management with id=%s Not Found", energyManagementId));
	}

}
