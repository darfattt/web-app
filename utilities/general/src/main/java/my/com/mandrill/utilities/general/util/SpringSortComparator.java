package my.com.mandrill.utilities.general.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.Comparator;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SpringSortComparator {

	public static <T> Comparator<T> compare(Sort.Order order, Comparator<T> comparator, Comparator<T> comparing) {
		if (order.isAscending()) {
			comparator = comparator == null ? comparing : comparator.thenComparing(comparing);
		}
		else {
			Comparator<T> descending = comparing.reversed();
			comparator = comparator == null ? descending : comparator.thenComparing(descending);
		}
		return comparator;
	}

}
