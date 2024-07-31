package my.com.mandrill.utilities.feign.client;

import lombok.NonNull;
import my.com.mandrill.utilities.feign.dto.*;
import my.com.mandrill.utilities.feign.dto.request.UploadTransactionRequest;
import my.com.mandrill.utilities.general.constant.EntityName;
import my.com.mandrill.utilities.general.dto.response.UserJourneyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@FeignClient("common-component")
public interface CommonFeignClient {

	@PostMapping("/admin/system-configurations/integration")
	SystemConfigurationDTO createSystemConfiguration(@RequestBody SystemConfigurationDTO systemConfigurationDto);

	@GetMapping("/admin/system-configurations")
	List<SystemConfigurationDTO> getAllSystemConfigurations(Pageable pageable,
			@RequestParam(required = false, name = "code") String code,
			@RequestParam(name = "institutionId") String institutionId);

	@GetMapping("system-config/no-pagination")
	List<SystemConfigurationDTO> getAllSystemConfigurationsNoPagination(
			@RequestParam(name = "institutionId") String institutionId);

	@GetMapping("/admin/system-configurations/{systemConfigurationId}")
	SystemConfigurationDTO getSystemConfigurationById(
			@PathVariable(name = "systemConfigurationId") String systemConfigurationId);

	@GetMapping("system-config/public/{institutionId}/{code}")
	SystemConfigurationDTO getSystemConfigurationByCodeAndInstitutionId(@PathVariable(name = "code") String code,
			@PathVariable(name = "institutionId") String institutionId);

	@GetMapping("system-config/{institutionId}/{code}")
	SystemConfigurationDTO getSystemConfigurationByCodeAndInstitutionIdReturnDefault(
			@PathVariable(name = "code") String code, @PathVariable(name = "institutionId") String institutionId);

	@PutMapping("/admin/system-configurations")
	SystemConfigurationDTO updateSystemConfiguration(@RequestBody SystemConfigurationDTO systemConfigurationDto);

	@DeleteMapping("/admin/system-configurations/{systemConfigurationId}")
	void deleteSystemConfigurationById(@PathVariable(name = "systemConfigurationId") String systemConfigurationId);

	@GetMapping("country/{id}")
	CountryDTO getCountry(@PathVariable(name = "id") String id);

	@GetMapping("state/{id}")
	StateDTO getState(@PathVariable(name = "id") String id);

	@PostMapping("attachment/upload")
	AttachmentGroupResponse uploadAttachment(@RequestBody AttachmentGroupDTO attachmentGroupDTO);

	@GetMapping("attachment/{attachmentGroupId}")
	AttachmentGroupResponse getAttachmentsByAttachmentGroupId(
			@PathVariable(name = "attachmentGroupId") String attachmentGroupId);

	@GetMapping("utility-types/{id}")
	UtilityTypeDTO findUtilityTypeByIdAndActiveTrue(@PathVariable(name = "id") String id);

	@GetMapping("user-journeys/{id}")
	UserJourneyResponse getUserJourneyById(@PathVariable(name = "id") @NonNull String id);

	@PostMapping("user-journeys/{name}")
	UserJourneyResponse createUserJourney(@PathVariable(name = "name") String name,
			@RequestBody UserJourneyRequest userJourneyRequest);

	@PostMapping("user-journeys/create/{groupName}")
	UserJourneyResponse createUserJourneyByGroupName(@PathVariable(name = "groupName") String groupName,
			@RequestBody UserJourneyRequest userJourneyRequest);

	@GetMapping("user-journeys/by-entity/{entityName}/{entityId}")
	ResponseEntity<List<UserJourneyResponse>> findUserJourneyByEntityName(
			@PathVariable(name = "entityName") EntityName entityName, @PathVariable(name = "entityId") String entityId);

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("user-journeys/by-entity/{entityName}/{entityId}")
	void deleteUserJourneyByEntityName(@PathVariable(name = "entityName") EntityName entityName,
			@PathVariable(name = "entityId") String entityId);

	@PostMapping("upload-transactions/integration")
	UploadTransactionDTO createUploadTransaction(@RequestBody UploadTransactionRequest uploadTransactionRequest);

	@PutMapping("upload-transactions/{id}")
	UploadTransactionDTO updateUploadTransaction(@RequestBody UploadTransactionRequest uploadTransactionRequest,
			@PathVariable(name = "id") String id);

	@GetMapping("vaults")
	List<VaultResponse> getAttachmentGroupByUserIdAndVaultType(@RequestParam(name = "vaultTypeId") String vaultTypeId);

	@GetMapping("vaults/{attachmentGroupId}")
	VaultResponse getAttachmentGroupByGroupId(@PathVariable(name = "attachmentGroupId") String attachmentGroupId);

	@PostMapping("attachment/upload-advertisement")
	AdvertisementDTO uploadAdvertisementBanner(
			@RequestBody AdvertisementAttachmentGroupDTO advertisementAttachmentGroupDTO);

	@GetMapping("lookup")
	List<LookupDTO> getLookupByLookupGroupCode(@RequestParam(name = "code") String code);

	@GetMapping("lookup/eligibility-details-checklist")
	List<LookupDTO> getEligibilityDetailsChecklistLookup();

	@PostMapping("excel/generate")
	public byte[] generateExcel(@RequestParam String sheetName, @RequestBody List<LinkedHashMap<String, String>> data);

	@GetMapping("lookup/by-id/{id}")
	String getLookupValueById(@PathVariable(name = "id") String id);

	@GetMapping("lookup/by-name")
	LookupDTO getLookupByName(@RequestParam(name = "name") String name);

}
