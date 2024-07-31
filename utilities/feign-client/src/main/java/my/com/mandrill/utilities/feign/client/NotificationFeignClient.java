package my.com.mandrill.utilities.feign.client;

import my.com.mandrill.utilities.feign.dto.ReminderRequest;
import my.com.mandrill.utilities.feign.dto.ReminderResponse;
import my.com.mandrill.utilities.feign.dto.request.EmailRequest;
import my.com.mandrill.utilities.general.constant.ReminderType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "notification-component")
public interface NotificationFeignClient {

	@PostMapping("/emails")
	void sendEmail(@RequestBody EmailRequest request, @RequestParam(name = "templateName") String templateName);

	@PostMapping("/sms")
	void sendSms(Object object, @RequestParam(name = "templateName") String templateName);

	@DeleteMapping("reminders/clean-up")
	void cleanUp(@RequestParam(name = "id") String userId, @RequestParam(name = "ids") List<String> ids,
			@RequestParam(name = "reminderType") ReminderType reminderType);

	@PostMapping("reminders/integration")
	void integration(@RequestBody ReminderRequest reminderRequest);

	@PutMapping("reminders/integration/{id}")
	ReminderResponse update(@RequestBody ReminderRequest updateReminderRequest, @PathVariable(name = "id") String id);

	@GetMapping("reminders/{reminderType}/{dataId}")
	ReminderResponse findByReminderTypeAndDataId(@PathVariable(name = "reminderType") ReminderType reminderType,
			@PathVariable(name = "dataId") String dataId);

	@DeleteMapping("reminders/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable(name = "id") String id);

}
