package my.com.mandrill.utilities.feign.client;

import my.com.mandrill.utilities.feign.dto.ClassificationDTO;
import jakarta.validation.Valid;
import my.com.mandrill.utilities.feign.dto.ModuleResponse;
import my.com.mandrill.utilities.feign.dto.ParaSportsDTO;
import my.com.mandrill.utilities.feign.dto.SportsDetailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("sports-component")
public interface SportsFeignClient {

	@GetMapping("/modules/subscription")
	List<ModuleResponse> getAllSubModuleListBySubscriptions(@RequestParam(required = true) String subscription,
			@RequestParam(required = true) boolean admin, @RequestParam(required = true) String institutionId);

	@GetMapping("/para-sports/{id}")
	ParaSportsDTO getById(@PathVariable String id);

	@GetMapping("/sports-detail/userId/{userId}")
	List<SportsDetailDTO> getAll(@PathVariable String userId);

	@GetMapping("/sports-detail/user-ids")
	List<SportsDetailDTO> getByUserIds(@RequestParam(required = false, name = "institutionId") String institutionId,
			@RequestParam List<String> userIds);

	@GetMapping("/sports-detail/institution/{institutionId}")
	List<SportsDetailDTO> getByInstitutionIdAndUserIds(@PathVariable String institutionId,
			@RequestParam List<String> userIds);

	@GetMapping("/classification/{id}")
	ClassificationDTO getClassificationById(@PathVariable String id);

	@PostMapping("/sports-detail/add")
	SportsDetailDTO createSportsDetail(@Valid @RequestBody SportsDetailDTO request);

	@GetMapping("/sports-detail/user-id-and-institution-id/{userId}/{institutionId}")
	SportsDetailDTO getByUserIdAndInstitutionId(@PathVariable String userId, @PathVariable String institutionId);

}
