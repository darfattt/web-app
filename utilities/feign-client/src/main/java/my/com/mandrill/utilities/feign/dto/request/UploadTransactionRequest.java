package my.com.mandrill.utilities.feign.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.com.mandrill.utilities.general.constant.UploadTransactionStatus;
import my.com.mandrill.utilities.general.constant.UploadTransactionType;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadTransactionRequest implements Serializable {

	@NotNull
	private String fileName;

	@NotNull
	private UploadTransactionStatus status;

	@NotNull
	private UploadTransactionType type;

	private Long uploadTime;

	private Long successRecords;

	private Long failRecords;

}
