package my.com.mandrill.utilities.general.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateConvertUtil {

	public static final String DATETIME_FORMAT_1 = "yyyyMMddHHmmss";

	public static final String DATETIME_FORMAT_2 = "MMM dd, YYYY, hh:mm:ss a";

	public static final String DATETIME_FORMAT_3 = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String DATETIME_FORMAT_4 = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String DATETIME_FORMAT_5 = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";

	public static final String DATE_FORMAT_1 = "yyyy-MM-dd";

	public static final String DATE_FORMAT_2 = "yyyyMMdd";

	public static final String DATE_FORMAT_3 = "dd/MM/yyyy";

	public static final String DATE_FORMAT_4 = "yyyy";

	public static final String DATE_FORMAT_5 = "MMM uuuu";

	public static final String DATE_FORMAT_6 = "MMM-yy";

	public static final String DATE_FORMAT_7 = "dd MMM yyyy";

	public static final String DATE_FORMAT_8 = "yyyyMMddHHmm";

	public static final String DATE_FORMAT_9 = "uuuuMM";

	public static final String TIME_FORMAT_1 = "hhmmssSSS"; // hhmmssfff?

	public static final String ISO_STRING = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	public static String toString(YearMonth date, String pattern) {
		if (date != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault());
			return formatter.format(date);
		}
		return null;
	}

	public static String toString(Instant date, String pattern, ZoneId zoneId) {
		if (date != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(zoneId);
			return formatter.format(date);
		}
		return null;
	}

	public static String toString(Instant date, String pattern) {
		if (date != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault());
			return formatter.format(date);
		}
		return null;
	}

	public static String toString(LocalDate date, String pattern) {
		if (date != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault());
			return formatter.format(date);
		}
		return null;
	}

	public static String toString(Date date, String pattern) {
		if (date != null) {
			DateFormat dateFormat = new SimpleDateFormat(pattern);
			return dateFormat.format(date);
		}
		return null;
	}

	public static Instant toInstant(String date, String pattern, boolean withTime) {
		if (date != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
			ZonedDateTime datetime;
			if (withTime) {
				datetime = LocalDateTime.parse(date, formatter).atZone(ZoneId.systemDefault());
			}
			else {
				datetime = LocalDate.parse(date, formatter).atStartOfDay(ZoneId.systemDefault());
			}

			return datetime.toInstant();
		}
		return null;
	}

	public static LocalDate toLocalDate(String date, String pattern) {
		if (date != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
			return LocalDate.parse(date, formatter);
		}
		return null;
	}

	public static Date toDate(String date, String pattern) {
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			try {
				return formatter.parse(date);
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static LocalDate toLocalDate(Instant instant) {
		if (instant != null) {
			return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
		}
		return null;
	}

	public static LocalDate toLocalDate(Date dateToConvert) {
		if (dateToConvert != null) {
			return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}
		return null;
	}

	public static LocalDateTime toLocalDateTime(Instant instant) {
		if (instant != null) {
			LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
			return localDateTime;
		}
		return null;
	}

	public static String convertDateFormatLocalDate(String dateString, String fromPattern, String toPattern) {
		return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(fromPattern))
				.format(DateTimeFormatter.ofPattern(toPattern));
	}

}