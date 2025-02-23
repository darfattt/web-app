package my.com.mandrill.utilities.general.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneySerializer extends JsonSerializer<BigDecimal> {

	@Override
	public void serialize(BigDecimal value, JsonGenerator jsonGenerator, SerializerProvider provider)
			throws IOException {
		jsonGenerator.writeString(value.setScale(2, RoundingMode.HALF_UP).toString());
	}

}
