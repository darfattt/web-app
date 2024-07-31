package my.com.mandrill.utilities.general.constant.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import my.com.mandrill.utilities.general.constant.Country;

import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CountryConverter implements AttributeConverter<Country, String> {

	@Override
	public String convertToDatabaseColumn(Country country) {
		return (Objects.isNull(country)) ? null : country.getCode();
	}

	@Override
	public Country convertToEntityAttribute(String code) {
		return (Objects.isNull(code)) ? null : Stream.of(Country.values()).filter(c -> c.getCode().equals(code))
				.findFirst().orElseThrow(IllegalArgumentException::new);
	}

}
