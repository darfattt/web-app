package my.com.mandrill.utilities.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "payment-component")
public interface PaymentFeignClient {

	@PostMapping("payment/trigger")
	void triggerPayment(Object object);

	@PostMapping(value = "payment/response", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	void responsePayment(Object object);

}
