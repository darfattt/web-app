package my.com.mandrill.utilities.general.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Objects;
import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.NUMBER)
public enum DeviceTypeEnum {

	// fixed integer value following OS api deviceType Section
	// https://github.com/OneSignal/onesignal-java-api/blob/main/docs/Player.md#player
	IOS(0), Android(1), Amazon(2), WindowsPhone(3), ChromeAppsExtension(4), ChromeWebPush(5), WindowWNS(6), Safari(7),
	Firefox(8), MacOS(9), Alexa(10), Email(11), HuaweiApp(12), NotForHuaweiDeviceUsingFCM(13), SMS(14);

	private Integer ordinal;

	DeviceTypeEnum(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public Integer getOrdinal() {
		return this.ordinal;
	}

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static DeviceTypeEnum fromCode(Integer code) {
		return (Objects.isNull(code)) ? null : Stream.of(DeviceTypeEnum.values())
				.filter(dt -> dt.getOrdinal().equals(code)).findFirst().orElseThrow(IllegalArgumentException::new);
	}

}
