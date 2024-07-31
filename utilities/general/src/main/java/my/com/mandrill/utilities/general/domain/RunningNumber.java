package my.com.mandrill.utilities.general.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(RunningNumberId.class)
@Table(name = "running_number",
		uniqueConstraints = { @UniqueConstraint(columnNames = { "module_name", "institution_id" }) })
public class RunningNumber implements Serializable {

	@Id
	@Column(name = "module_name", updatable = false, nullable = false, length = 100)
	private String module;

	@Column(name = "running_number", nullable = false)
	private Long next;

	@Column(nullable = false, length = 10)
	private String prefix;

	@Column(name = "number_format", nullable = false, length = 10, columnDefinition = "VARCHAR(10) DEFAULT '%d'")
	private String format;

	@Column(name = "include_day", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean includeDay = Boolean.FALSE;

	@Id
	@Column(name = "institution_id", nullable = false, length = 36)
	private String institutionId;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		RunningNumber that = (RunningNumber) o;
		return module != null && Objects.equals(module, that.module);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
