package my.com.mandrill.component.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.component.config.MapStructConverter;
import my.com.mandrill.component.domain.Domain;
import my.com.mandrill.component.dto.request.DomainRequestDTO;
import my.com.mandrill.component.dto.response.DomainResponseDTO;
import my.com.mandrill.component.service.DomainService;
import my.com.mandrill.utilities.general.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.BulkFailureException;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/domain")
public class DomainController {

	private final DomainService domainService;

	public DomainController(DomainService domainService) {
		this.domainService = domainService;
	}

	@PostMapping
	public ResponseEntity<DomainResponseDTO> createDomain(@Valid @RequestBody DomainRequestDTO domainRequestDTO) {
		Domain domain = domainService.save(MapStructConverter.MAPPER.convert(domainRequestDTO));
		DomainResponseDTO domainResponseDTO = MapStructConverter.MAPPER.convert(domain);
		return ResponseEntity.ok(domainResponseDTO);
	}

	@PutMapping
	public ResponseEntity<DomainResponseDTO> updateDomain(@Valid @RequestBody DomainRequestDTO domainRequestDTO) {
		Domain domain = domainService.updateDomain(MapStructConverter.MAPPER.convert(domainRequestDTO));
		domain = domainService.save(domain);
		DomainResponseDTO domainResponseDTO = MapStructConverter.MAPPER.convert(domain);
		return ResponseEntity.ok(domainResponseDTO);
	}

	@GetMapping("{code}/{name}")
	public ResponseEntity<DomainResponseDTO> getDomainByCodeAndName(@PathVariable String code,
			@PathVariable String name) {
		Domain domain = domainService.getDomainByCodeAndName(code, name);
		DomainResponseDTO domainResponseDTO = MapStructConverter.MAPPER.convert(domain);
		return ResponseEntity.ok(domainResponseDTO);
	}

	@GetMapping("{id}")
	public ResponseEntity<DomainResponseDTO> getDomainById(@PathVariable String id) {
		Domain domain = domainService.getDomainById(id);
		DomainResponseDTO domainResponseDTO = MapStructConverter.MAPPER.convert(domain);
		return ResponseEntity.ok(domainResponseDTO);
	}

	@GetMapping
	public ResponseEntity<List<DomainResponseDTO>> getDomains(@PageableDefault Pageable pageable) {
		Page<Domain> domain = domainService.getDomains(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(domain, "domain");
		List<DomainResponseDTO> domainResponseDTOS = MapStructConverter.MAPPER.convert(domain.toList());
		return ResponseEntity.ok().headers(headers).body(domainResponseDTOS);
	}

	@GetMapping("search")
	public ResponseEntity<List<DomainResponseDTO>> searchDomains(@PageableDefault Pageable pageable) {
		Page<Domain> domain = domainService.searchDomains(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(domain, "domain/search");
		List<DomainResponseDTO> domainResponseDTOS = MapStructConverter.MAPPER.convert(domain.toList());
		return ResponseEntity.ok().headers(headers).body(domainResponseDTOS);
	}

	@GetMapping("search/refresh")
	public Object refreshSearchIndex() {
		try {
			domainService.refreshSearchIndex();
		}
		catch (BulkFailureException bulkFailureException) {
			return bulkFailureException.getFailedDocuments();
		}
		return "OK";
	}

}
