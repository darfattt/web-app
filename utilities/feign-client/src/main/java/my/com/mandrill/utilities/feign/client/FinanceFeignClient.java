package my.com.mandrill.utilities.feign.client;

import my.com.mandrill.utilities.feign.dto.ModuleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("finance-component")
public interface FinanceFeignClient {

	@GetMapping("/modules/subscription")
	List<ModuleResponse> getAllSubModuleListBySubscriptions(@RequestParam(required = true) String subscription,
			@RequestParam(required = true) boolean admin, @RequestParam(required = true) String institutionId);

}
