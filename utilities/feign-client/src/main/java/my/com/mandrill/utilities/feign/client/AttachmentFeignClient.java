package my.com.mandrill.utilities.feign.client;

import my.com.mandrill.utilities.feign.dto.AttachmentGroupDTO;
import my.com.mandrill.utilities.feign.dto.AttachmentGroupResponse;
import my.com.mandrill.utilities.feign.dto.VaultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("common-component/attachment")
public interface AttachmentFeignClient {

	@PostMapping("/upload")
	AttachmentGroupResponse uploadAttachment(@RequestBody AttachmentGroupDTO attachmentGroupDTO);

	@GetMapping("/{attachmentGroupId}")
	AttachmentGroupResponse getAttachmentsByGroupId(@PathVariable(name = "attachmentGroupId") String attachmentGroupId);

	@GetMapping("/attachment-group/{attachmentGroupId}")
	AttachmentGroupDTO getAttachmentGroupByGroupId(@PathVariable(name = "attachmentGroupId") String attachmentGroupId);

}
