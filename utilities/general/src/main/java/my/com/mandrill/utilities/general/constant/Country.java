package my.com.mandrill.utilities.general.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Country {

	MALAYSIA("MY");

	private String code;

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static Country getCountryFromCode(String code) {
		return (Objects.isNull(code)) ? null : Stream.of(Country.values()).filter(c -> c.getCode().equals(code))
				.findFirst().orElseThrow(IllegalArgumentException::new);
	}

	@JsonValue
	public String value() {
		return code;
	}

	@Override
	public String toString() {
		return code;
	}

}
