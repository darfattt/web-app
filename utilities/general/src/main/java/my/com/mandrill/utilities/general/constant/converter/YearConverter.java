package my.com.mandrill.utilities.general.constant.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Year;
import java.util.Objects;

@Converter(autoApply = true)
public class YearConverter implements AttributeConverter<Year, Short> {

	@Override
	public Short convertToDatabaseColumn(Year attribute) {
		return Objects.nonNull(attribute) ? (short) attribute.getValue() : null;
	}

	@Override
	public Year convertToEntityAttribute(Short dbData) {
		return Objects.nonNull(dbData) ? Year.of(dbData) : null;
	}

}
