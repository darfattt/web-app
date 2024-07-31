package my.com.mandrill.component.repository.search;

import my.com.mandrill.component.domain.Domain;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainSearchRepository extends ElasticsearchRepository<Domain, String> {

}
