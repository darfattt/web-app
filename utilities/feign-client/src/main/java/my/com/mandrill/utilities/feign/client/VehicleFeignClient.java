package my.com.mandrill.utilities.feign.client;

import my.com.mandrill.utilities.feign.dto.NetWorthDTO;
import my.com.mandrill.utilities.feign.dto.VaultLinkDTO;
import my.com.mandrill.utilities.feign.dto.VehicleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "vehicle-component")
public interface VehicleFeignClient {

	@GetMapping("/vehicles/{id}")
	VehicleDTO findVehicleById(@PathVariable(name = "id") String id);

	@GetMapping("/vehicles")
	List<VehicleDTO> findAll(Sort sort);

	@PutMapping("/vehicles/vault/link/{id}")
	void linkVault(@RequestBody VaultLinkDTO vaultLinkDTO, @PathVariable(name = "id") String id);

	@GetMapping("vehicles/vault/linked/{attachmentGroupId}")
	VehicleDTO findLinkedVault(@PathVariable(name = "attachmentGroupId") String attachmentGroupId);

	@GetMapping("/vehicles/vault/unlinked")
	List<VehicleDTO> getVehicleWithAttachmentGroupIdNull();

	@GetMapping("/vehicles/count")
	Long count();

	@GetMapping("/vehicles/vault/is-linked/{attachmentGroupId}")
	Boolean existsByUserIdAndAttachmentGroupId(@PathVariable(name = "attachmentGroupId") String attachmentGroupId);

	@GetMapping("/vehicles/integration/net-worth")
	NetWorthDTO calculateNetWorth();

}
