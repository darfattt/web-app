package my.com.mandrill.component.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import my.com.mandrill.component.constant.ConstantEnum;
import my.com.mandrill.utilities.core.audit.AuditSection;
import org.hibernate.Hibernate;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "domain")
@Entity
@Table(name = "domain", uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "name" }) })
public class Domain extends AuditSection {

	@NotBlank
	@Size(max = 100)
	@Column(name = "code", nullable = false, length = 100)
	private String code;

	@NotBlank
	@Size(max = 100)
	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Size(max = 200)
	@Column(name = "description", length = 200)
	private String description;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private ConstantEnum type;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		Domain that = (Domain) o;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}