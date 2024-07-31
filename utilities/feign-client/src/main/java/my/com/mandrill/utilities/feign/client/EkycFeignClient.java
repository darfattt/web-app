package my.com.mandrill.utilities.feign.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ekyc-component")
public interface EkycFeignClient {

}
