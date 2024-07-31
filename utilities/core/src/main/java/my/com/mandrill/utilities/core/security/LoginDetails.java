package my.com.mandrill.utilities.core.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import my.com.mandrill.utilities.general.constant.AccessTypeEnum;
import my.com.mandrill.utilities.general.constant.LoginTypeEnum;

@Data
@AllArgsConstructor
public class LoginDetails {

	private AccessTypeEnum accessType;

	private LoginTypeEnum loginType;

	private String institutionName;

	public LoginDetails(AccessTypeEnum accessType, LoginTypeEnum loginType) {
		this.accessType = accessType;
		this.loginType = loginType;
	}

}
