package my.com.mandrill.utilities.feign.dto;

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
public class UploadTransactionDTO implements Serializable {

	private String id;

	private String fileName;

	private UploadTransactionStatus status;

	private UploadTransactionType type;

	private Long uploadTime;

	private Long successRecords;

	private Long failRecords;

}
