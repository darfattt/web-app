package my.com.mandrill.utilities.general.repository;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import my.com.mandrill.utilities.general.domain.RunningNumber;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RunningNumberRepository extends JpaRepository<RunningNumber, String> {

	@Modifying
	@Query("UPDATE RunningNumber rn SET rn.next = :latestNumber WHERE rn.module = :module AND rn.institutionId = :institutionId")
	void updateRunningNumber(@Param("latestNumber") Long latestNumber, @Param("module") String module,
			@Param("institutionId") String institutionId);

	// to check the capability on kubernetes
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({ @QueryHint(name = "javax.persistence.lock.timeout", value = "5000") })
	Optional<RunningNumber> findByModuleAndInstitutionId(String module, String institutionId);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({ @QueryHint(name = "javax.persistence.lock.timeout", value = "5000") })
	Optional<RunningNumber> findFirstByModule(String module);

	List<RunningNumber> findAllByModule(String module);

}
