package my.com.mandrill.utilities.general.util;

import com.google.i18n.phonenumbers.Phonenumber;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.com.mandrill.utilities.general.exception.PhoneNumberException;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PhoneNumberUtil {

	public static String formatPhoneNumber(String phoneCountry, String phoneNumber) {
		return phoneCountry.replace("+", "") + phoneNumber;
	}

	public static ExtractedPhoneNumber getPhoneNumberWithNoCountryCode(String phoneNumberString) {
		com.google.i18n.phonenumbers.PhoneNumberUtil phoneNumberUtil = com.google.i18n.phonenumbers.PhoneNumberUtil
				.getInstance();
		Phonenumber.PhoneNumber phoneNumber;
		try {
			phoneNumber = phoneNumberUtil.parse(phoneNumberString,
					Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name());
		}
		catch (Exception e) {
			throw new PhoneNumberException("Phone: " + phoneNumberString);
		}

		if (phoneNumberUtil.isValidNumber(phoneNumber)) {
			return new ExtractedPhoneNumber("+" + phoneNumber.getCountryCode(),
					Long.toString(phoneNumber.getNationalNumber()));
		}
		else {
			throw new PhoneNumberException("Phone: " + phoneNumberString);
		}
	}

	@Data
	public static class ExtractedPhoneNumber implements Serializable {

		private String phoneCountry;

		private String phoneNumber;

		private String combinedPhoneNumber;

		public ExtractedPhoneNumber(String phoneCountry, String phoneNumber) {
			this.phoneCountry = phoneCountry;
			this.phoneNumber = phoneNumber;
			this.combinedPhoneNumber = phoneCountry + phoneNumber;
		}

	}

}
