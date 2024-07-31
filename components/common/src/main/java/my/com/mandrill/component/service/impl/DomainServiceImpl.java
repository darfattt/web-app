package my.com.mandrill.component.service.impl;

import my.com.mandrill.component.domain.Domain;
import my.com.mandrill.component.repository.jpa.DomainRepository;
import my.com.mandrill.component.repository.search.DomainSearchRepository;
import my.com.mandrill.component.service.DomainService;
import my.com.mandrill.utilities.general.exception.ExceptionPredicate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DomainServiceImpl implements DomainService {

	private final DomainRepository domainRepository;

	private final DomainSearchRepository domainSearchRepository;

	private final ElasticsearchOperations elasticsearchOperations;

	public DomainServiceImpl(DomainRepository domainRepository, DomainSearchRepository domainSearchRepository,
			ElasticsearchOperations elasticsearchOperations) {
		this.domainRepository = domainRepository;
		this.domainSearchRepository = domainSearchRepository;
		this.elasticsearchOperations = elasticsearchOperations;
	}

	@Override
	public Domain getDomainById(String id) {
		return domainRepository.findById(id).orElseThrow(ExceptionPredicate.domainNotFound(id));
	}

	@Override
	public Domain getDomainByCodeAndName(String code, String name) {
		return domainRepository.findByCodeAndName(code, name)
				.orElseThrow(ExceptionPredicate.domainNotFound(code, name));
	}

	@Override
	@Transactional
	@CacheEvict(value = ALL_DOMAIN_CACHE, allEntries = true)
	public Domain save(Domain domain) {
		return domainRepository.save(domain);
	}

	@Override
	public Domain updateDomain(Domain domain) {
		Domain existingDomain = getDomainById(domain.getId());
		existingDomain.setDescription(domain.getDescription());
		return existingDomain;
	}

	@Override
	@Cacheable(value = ALL_DOMAIN_CACHE)
	public Page<Domain> getDomains(Pageable pageable) {
		return domainRepository.findAll(pageable);
	}

	@Override
	public Page<Domain> searchDomains(Pageable pageable) {
		return domainSearchRepository.findAll(pageable);
	}

	@Override
	public void refreshSearchIndex() {
		IndexOperations indexOperations = elasticsearchOperations.indexOps(Domain.class);
		domainSearchRepository.deleteAll();
		indexOperations.delete();
		indexOperations.create();
		domainSearchRepository.saveAll(domainRepository.findAll());
	}

}
