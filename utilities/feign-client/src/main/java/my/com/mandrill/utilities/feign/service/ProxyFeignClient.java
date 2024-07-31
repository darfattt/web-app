package my.com.mandrill.utilities.feign.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import my.com.mandrill.utilities.feign.client.*;
import org.springframework.stereotype.Service;

@Getter
@Service
@RequiredArgsConstructor
public class ProxyFeignClient {

	private final AccountFeignClient accountFeignClient;

	private final BankFeignClient bankFeignClient;

	private final CommonFeignClient commonFeignClient;

	private final InsuranceFeignClient insuranceFeignClient;

	private final PropertyFeignClient propertyFeignClient;

	private final UtilityFeignClient utilityFeignClient;

	private final VehicleFeignClient vehicleFeignClient;

}
