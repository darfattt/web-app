package my.com.mandrill.utilities.feign.client;

import my.com.mandrill.utilities.feign.dto.NetWorthDTO;
import my.com.mandrill.utilities.feign.dto.PropertyDTO;
import my.com.mandrill.utilities.feign.dto.PropertyStagingDTO;
import my.com.mandrill.utilities.feign.dto.VaultLinkDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("property-component")
public interface PropertyFeignClient {

	@GetMapping("properties")
	List<PropertyDTO> getProperties();

	@PostMapping("/property-staging")
	PropertyStagingDTO createPropertyStaging(@RequestBody PropertyStagingDTO propertyStagingDTO);

	@GetMapping("/property-staging")
	List<PropertyStagingDTO> getPropertyStaging();

	@GetMapping("properties/{id}")
	PropertyDTO findById(@PathVariable(name = "id") String id);

	@GetMapping("/properties/check-address")
	boolean isPropertyExistByAddress(@SpringQueryMap PropertyStagingDTO propertyStagingDTO);

	@DeleteMapping("/property-staging/by-address")
	void deletePropertyStagingByAddress(@SpringQueryMap PropertyStagingDTO propertyStagingDTO);

	@DeleteMapping("property-staging/utility/{utilityId}")
	void deletePropertyStagingByUtilityId(@PathVariable(name = "utilityId") String utilityId);

	@PutMapping("/properties/vault/link/{id}")
	void linkVault(@RequestBody VaultLinkDTO vaultLinkDTO, @PathVariable(name = "id") String id);

	@GetMapping("properties/vault/linked/{attachmentGroupId}")
	PropertyDTO findLinkedVault(@PathVariable(name = "attachmentGroupId") String attachmentGroupId);

	@GetMapping("/properties/vault/unlinked")
	List<PropertyDTO> getPropertyWithAttachmentGroupIdNull();

	@GetMapping("/properties/count")
	Long count();

	@GetMapping("/properties/vault/is-linked/{attachmentGroupId}")
	Boolean existsByUserIdAndAttachmentGroupId(@PathVariable(name = "attachmentGroupId") String attachmentGroupId);

	@GetMapping("/properties/integration/net-worth")
	NetWorthDTO calculateNetWorth();

}
