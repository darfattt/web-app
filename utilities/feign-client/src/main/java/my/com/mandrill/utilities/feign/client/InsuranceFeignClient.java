package my.com.mandrill.utilities.feign.client;

import my.com.mandrill.utilities.feign.dto.InsuranceDTO;
import my.com.mandrill.utilities.feign.dto.InsuranceTypeDTO;
import my.com.mandrill.utilities.feign.dto.NetWorthDTO;
import my.com.mandrill.utilities.feign.dto.VaultLinkDTO;
import my.com.mandrill.utilities.general.constant.InsuranceTypeEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "insurance-component")
public interface InsuranceFeignClient {

	@PostMapping("/insurances/integration")
	InsuranceDTO insuranceIntegration(@RequestBody InsuranceDTO insuranceDTO);

	@GetMapping("/insurances/{type}/{entityId}")
	InsuranceDTO findByTypeAndEntityId(@PathVariable(name = "type") InsuranceTypeEnum type,
			@PathVariable(name = "entityId") String entityId);

	@GetMapping("/insurances/{id}")
	InsuranceDTO findById(@PathVariable(name = "id") String id);

	@GetMapping("/insurances")
	List<InsuranceDTO> findAll(Sort sort);

	@PutMapping("/insurances/integration/{id}")
	InsuranceDTO updateInsurance(@RequestBody InsuranceDTO insuranceDTO, @PathVariable(name = "id") String id);

	@DeleteMapping("/insurances/{id}")
	void deleteInsurance(@PathVariable(name = "id") String id);

	@GetMapping("/insurances/count")
	Long count();

	@PutMapping("/insurances/vault/link/{id}")
	void linkVault(@RequestBody VaultLinkDTO vaultLinkDTO, @PathVariable(name = "id") String id);

	@GetMapping("insurances/vault/linked/{attachmentGroupId}")
	InsuranceDTO findLinkedVault(@PathVariable(name = "attachmentGroupId") String attachmentGroupId);

	@GetMapping("/insurances/vault/unlinked")
	List<InsuranceDTO> getInsuranceWithAttachmentGroupIdNull();

	@GetMapping("/types/{id}")
	InsuranceTypeDTO findTypeByIdAndActiveTrue(@PathVariable(name = "id") String id);

	@GetMapping("/insurances/integration/net-worth")
	NetWorthDTO calculateNetWorth();

	@GetMapping("/insurances/vault/is-linked/{attachmentGroupId}")
	Boolean existsByUserIdAndAttachmentGroupId(@PathVariable(name = "attachmentGroupId") String attachmentGroupId);

	@GetMapping("/providers/integration/issuer-codes")
	String getIssuerCodesByIsPartnerTrue();

}
