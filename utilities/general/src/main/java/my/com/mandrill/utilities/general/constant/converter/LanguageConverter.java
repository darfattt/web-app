package my.com.mandrill.utilities.general.constant.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import my.com.mandrill.utilities.general.constant.Language;

import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class LanguageConverter implements AttributeConverter<Language, String> {

	@Override
	public String convertToDatabaseColumn(Language language) {
		return (Objects.isNull(language)) ? null : language.getCode();
	}

	@Override
	public Language convertToEntityAttribute(String code) {
		return (Objects.isNull(code)) ? null : Stream.of(Language.values()).filter(l -> l.getCode().equals(code))
				.findFirst().orElseThrow(IllegalArgumentException::new);
	}

}
