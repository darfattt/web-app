package my.com.mandrill.utilities.feign.client;

import my.com.mandrill.utilities.feign.dto.JobDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "scheduler-component", path = "schedulers")
public interface SchedulerFeignClient {

	@PostMapping
	void createJob(@RequestBody JobDTO jobDTO);

	@GetMapping("all")
	List<JobDTO> getAllJobs();

	@GetMapping("exists")
	boolean isExists(@RequestParam(name = "group") String group, @RequestParam(name = "name") String name);

	@PutMapping("pause")
	boolean pauseJob(@RequestParam(name = "group") String group, @RequestParam(name = "name") String name);

	@PutMapping("resume")
	boolean resumeJob(@RequestParam(name = "group") String group, @RequestParam(name = "name") String name);

	@DeleteMapping
	boolean deleteJob(@RequestParam(name = "group") String group, @RequestParam(name = "name") String name);

	@PutMapping
	boolean updateJob(@RequestBody JobDTO jobDTO);

}
