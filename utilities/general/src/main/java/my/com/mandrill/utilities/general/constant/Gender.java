package my.com.mandrill.utilities.general.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Gender {

	MALE("M"), FEMALE("F"), UNISEX("U");

	private String code;

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static Gender getGenderFromCode(String code) {
		return (Objects.isNull(code)) ? null : Stream.of(Gender.values()).filter(g -> g.getCode().equals(code))
				.findFirst().orElseThrow(IllegalArgumentException::new);
	}

	@Override
	public String toString() {
		return code;
	}

}
