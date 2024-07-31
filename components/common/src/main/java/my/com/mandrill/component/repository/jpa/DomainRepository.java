package my.com.mandrill.component.repository.jpa;

import my.com.mandrill.component.constant.ConstantEnum;
import my.com.mandrill.component.domain.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DomainRepository extends JpaRepository<Domain, String> {

	Optional<Domain> findByCodeAndName(@NonNull String code, @NonNull String name);

	List<Domain> findByType(@NonNull ConstantEnum type);

}