package my.com.mandrill.utilities.feign.client;

import jakarta.validation.Valid;
import my.com.mandrill.utilities.feign.dto.*;
import my.com.mandrill.utilities.feign.dto.model.UserInterestRecordDTO;
import my.com.mandrill.utilities.general.constant.EntityName;
import my.com.mandrill.utilities.general.util.DateUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@FeignClient("bank-component")
public interface BankFeignClient {

	@GetMapping("bank")
	BankResponse getBank();

	@GetMapping("/bank/{id}")
	BankDTO getBankById(@PathVariable(name = "id") String id);

	@PostMapping("/loans/integration")
	LoanDTO loanIntegration(@RequestBody LoanDTO insuranceDTO);

	@GetMapping("/loans/{entityName}/{entityId}")
	LoanDTO findLoanByEntityNameAndEntityId(@PathVariable(name = "entityName") EntityName entityName,
			@PathVariable(name = "entityId") String entityId);

	@PutMapping("/loans/integration/{id}")
	LoanDTO updateLoan(@RequestBody LoanDTO loanDTO, @PathVariable(name = "id") String id);

	@DeleteMapping("/loans/{id}")
	void deleteLoan(@PathVariable(name = "id") String id);

	@GetMapping("/loans/{id}")
	LoanDTO findLoanById(@PathVariable(name = "id") String id);

	@GetMapping("/loans")
	List<LoanDTO> findLoanAll(Sort sort);

	@PutMapping("bank/vault/link/{id}")
	void linkVault(@PathVariable(name = "id") String id, @Valid @RequestBody VaultLinkDTO vaultLinkDTO);

	@GetMapping("bank/bank-detail/{id}")
	BankDetailDTO findBankDetailById(@PathVariable(name = "id") String id);

	@GetMapping("bank/vault/linked/{attachmentGroupId}")
	BankDetailVaultLinkDTO findLinkedVault(@PathVariable(name = "attachmentGroupId") String attachmentGroupId);

	@GetMapping("bank/vault/unlinked")
	List<BankDetailVaultLinkDTO> getBankDetailWithAttachmentGroupIdNull();

	@GetMapping("bank/count")
	Long countBank(@RequestParam(name = "entityName") EntityName entityName);

	@GetMapping("loans/count")
	Long countLoan();

	@GetMapping("/bank/vault/is-linked/{attachmentGroupId}")
	Boolean existsByUserIdAndAttachmentGroupId(@PathVariable(name = "attachmentGroupId") String attachmentGroupId);

	@GetMapping("/bank/integration/net-worth")
	NetWorthDTO calculateNetWorth();

	@GetMapping("/bank-list/integration/issuer-codes")
	String getIssuerCodesByIsPartnerTrue();

	@GetMapping("/user-interest-record/integration")
	List<UserInterestRecordDTO> userInterestRecord(
			@RequestParam(name = "startDate") @DateTimeFormat(pattern = DateUtil.DATE_FORMAT) LocalDate startDate,
			@RequestParam(name = "endDate") @DateTimeFormat(pattern = DateUtil.DATE_FORMAT) LocalDate endDate);

}
