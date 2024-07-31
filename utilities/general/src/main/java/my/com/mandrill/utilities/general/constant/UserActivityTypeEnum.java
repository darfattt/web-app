package my.com.mandrill.utilities.general.constant;

import java.util.Objects;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserActivityTypeEnum {

	LOGIN(new String[] { "/authenticate", "/auth-token" }), DOWNLOAD(new String[] { "/download" }),
	UPLOAD(new String[] { "/upload" }), OTHERS(new String[] { "-" });

	private String[] requestUrls;

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static UserActivityTypeEnum fromRequestUrls(String requestParam) {
		return (Objects.isNull(requestParam)) ? OTHERS : Stream.of(UserActivityTypeEnum.values())
				.filter(activity -> Stream.of(activity.getRequestUrls()).anyMatch(url -> requestParam.contains(url)))
				.findFirst().orElse(OTHERS);
	}

}
