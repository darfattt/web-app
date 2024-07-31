package my.com.mandrill.utilities.core.audit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
@EntityListeners(value = AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditSection implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(updatable = false, nullable = false, length = 36)
	private String id;

	@Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
	@CreatedDate
	@Column(name = "created_date", nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT NOW()")
	private Instant createdDate = Instant.now();

	@CreatedBy
	@Column(name = "created_by", nullable = false, length = 100, updatable = false,
			columnDefinition = "VARCHAR(100) DEFAULT 'sys-admin'")
	private String createdBy;

	@Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
	@LastModifiedDate
	@Column(name = "last_modified_date", columnDefinition = "DATETIME DEFAULT NOW()")
	private Instant lastModifiedDate = Instant.now();

	@LastModifiedBy
	@Column(name = "last_modified_by", length = 100)
	private String lastModifiedBy;

}
