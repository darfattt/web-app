package my.com.mandrill.utilities.general.util;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import my.com.mandrill.utilities.general.constant.TimeConstant;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.time.*;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {

	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy - HH:mm:ss z";

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final DateTimeFormatter defaultDateFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);

	public static final DateTimeFormatter BIG_QUERY_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

	public static final DateTimeFormatter DD_MMM_YY = DateTimeFormatter.ofPattern("dd MMM yy");

	public static final DateTimeFormatter EEEE_DD_MMM_YY = DateTimeFormatter.ofPattern("EEEE dd MMM yy");

	public static final DateTimeFormatter EEEE = DateTimeFormatter.ofPattern("EEEE");

	public static final DateTimeFormatter MMMM_YY = DateTimeFormatter.ofPattern("MMMM yy");

	public static final DateTimeFormatter DD_MMMM_YYYY = DateTimeFormatter.ofPattern("dd MMMM yyyy");

	/**
	 * This is used in Jasper Report Please do not delete
	 * @param instant - time
	 * @return formatted string
	 */
	@SuppressWarnings("unused")
	public static String instantToString(Instant instant) {
		return instant.atZone(ZoneId.of(TimeConstant.DEFAULT_TIMEZONE)).format(DateUtil.defaultDateFormatter);
	}

	public static boolean isAfterToday(LocalDate localDate) {
		LocalDate today = LocalDate.now(ZoneId.of(TimeConstant.DEFAULT_TIMEZONE));
		return localDate.isAfter(today);
	}

	public static boolean isBeforeTomorrow(LocalDate localDate) {
		return !isAfterToday(localDate);
	}

	public static boolean isSameDay(Instant first, Instant second) {
		return LocalDate.ofInstant(first, ZoneOffset.UTC).equals(LocalDate.ofInstant(second, ZoneOffset.UTC));
	}

	public static LocalDate getReminderEndDate(@NotNull Short duration, @NotNull Year repaymentStartYear,
			@NotNull LocalDate startDate, @NotNull Month repaymentStartMonth) {

		int remainingYear = duration
				- (Year.now(ZoneId.of(TimeConstant.DEFAULT_TIMEZONE)).getValue() - repaymentStartYear.getValue());

		return startDate.plusYears(remainingYear).withMonth(repaymentStartMonth.getValue());
	}

	public static boolean equalOrAfter(Instant first, Instant second) {
		return first.compareTo(second) >= 0;
	}

	public static String toString(Instant instant) {
		return instant.atZone(ZoneId.of(TimeConstant.DEFAULT_TIMEZONE)).format(DateUtil.DD_MMMM_YYYY);
	}

	public static String toString(LocalDate localDate) {
		return localDate.format(DateUtil.DD_MMMM_YYYY);
	}

	public static DateTime now() {
		return DateTime.now(DateTimeZone.forID(TimeConstant.APPLICATION_TIMEZONE));
	}

	public static Instant toInstant(LocalDateTime localDateTime) {
		if (localDateTime != null) {
			return localDateTime.atZone(ZoneId.of(TimeConstant.DEFAULT_TIMEZONE)).toInstant();
		}
		return null;
	}

}
