package my.com.mandrill.utilities.feign.client;

import jakarta.validation.Valid;
import my.com.mandrill.utilities.feign.dto.UtilityDTO;
import my.com.mandrill.utilities.feign.dto.VaultLinkDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "utility-component")
public interface UtilityFeignClient {

	@GetMapping("/utilities/feign-client/{id}")
	UtilityDTO findUtilityById(@PathVariable(name = "id") String id);

	@GetMapping("utilities")
	List<UtilityDTO> getUtility();

	@GetMapping("utilities/{id}")
	UtilityDTO getUtilityById(@PathVariable(name = "id") String id);

	@PutMapping("utilities/vault/link/{id}")
	void linkVault(@PathVariable(name = "id") String id, @Valid @RequestBody VaultLinkDTO vaultLinkDTO);

	@GetMapping("utilities/vault/linked/{attachmentGroupId}")
	UtilityDTO findLinkedVault(@PathVariable(name = "attachmentGroupId") String attachmentGroupId);

	@GetMapping("/utilities/vault/unlinked")
	List<UtilityDTO> getUtilityWithAttachmentGroupIdNull();

	@GetMapping("/utilities/count")
	Long count();

	@GetMapping("/utilities/vault/is-linked/{attachmentGroupId}")
	Boolean existsByUserIdAndAttachmentGroupId(@PathVariable(name = "attachmentGroupId") String attachmentGroupId);

}
