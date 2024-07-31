package my.com.mandrill.utilities.feign.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationKeyDTO {

	private String id;

	private String recipientEmail;

	private String recipientName;

	private String institutionName;

	private String registerKey;

	private LocalDate expiredDate;

	private String esgAccess;

	private String financeAccess;

	private String sportsAccess;

}
