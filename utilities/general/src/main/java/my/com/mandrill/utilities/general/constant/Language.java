package my.com.mandrill.utilities.general.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Language {

	@JsonProperty("en")
	ENGLISH("en");

	private final String code;

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static Language getLanguageFromCode(String code) {
		return (Objects.isNull(code)) ? null : Stream.of(Language.values()).filter(l -> l.getCode().equals(code))
				.findFirst().orElseThrow(IllegalArgumentException::new);
	}

}
