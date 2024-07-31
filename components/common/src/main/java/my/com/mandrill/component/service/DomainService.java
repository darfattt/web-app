package my.com.mandrill.component.service;

import my.com.mandrill.component.domain.Domain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DomainService {

	String ALL_DOMAIN_CACHE = "{ALL_DOMAIN_CACHE}";

	Domain getDomainById(String id);

	Domain getDomainByCodeAndName(String code, String name);

	Domain save(Domain domain);

	Domain updateDomain(Domain domain);

	Page<Domain> getDomains(Pageable pageable);

	Page<Domain> searchDomains(Pageable pageable);

	void refreshSearchIndex();

}
