package my.com.mandrill.utilities.general.dto;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.experimental.Delegate;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://stackoverflow.com/a/55154919">See this</a>
 *
 * @param <E>
 */
@Data
public class ValidList<E> implements List<E> {

	@Valid
	@Delegate
	private final List<E> list = new ArrayList<>();

}
