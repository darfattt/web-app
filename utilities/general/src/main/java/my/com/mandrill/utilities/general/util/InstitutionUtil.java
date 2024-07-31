package my.com.mandrill.utilities.general.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import my.com.mandrill.utilities.general.constant.HttpHeaderKeyEnum;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InstitutionUtil {

	public static String getInstitutionHeader(HttpServletRequest request) {
		String institutionIdHeader = request.getHeader(HttpHeaderKeyEnum.INSTITUTION.getKey());
		String subInstitutionIdHeader = request.getHeader(HttpHeaderKeyEnum.SUB_INSTITUTION.getKey());
		if (subInstitutionIdHeader != null) {
			return subInstitutionIdHeader;
		}
		return institutionIdHeader;
	}

}
