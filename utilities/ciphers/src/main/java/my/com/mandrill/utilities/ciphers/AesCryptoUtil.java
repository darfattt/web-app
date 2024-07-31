package my.com.mandrill.utilities.ciphers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;

@Slf4j
@Component
public final class AesCryptoUtil {

	private static String AES_KEY;

	private static String IV_KEY;

	private static final String ALGORITHM = "AES/GCM/NoPadding";

	private static final int GCM_TAG_LENGTH = 112; // 128, 120, 112, 104, 96

	@Value("${securities.crypto.key}")
	public void setAesKey(String key) {
		AesCryptoUtil.AES_KEY = key;
	}

	@Value("${securities.crypto.iv}")
	public void setIvKey(String iv) {
		AesCryptoUtil.IV_KEY = iv;
	}

	public static String encrypt(String plainText, String iv) {
		Key key = new SecretKeySpec(Base64.getDecoder().decode(AES_KEY), "AES");
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			if (iv != null) {
				GCMParameterSpec gcm = new GCMParameterSpec(GCM_TAG_LENGTH, iv.getBytes());
				cipher.init(Cipher.ENCRYPT_MODE, key, gcm);
			}
			else {
				cipher.init(Cipher.ENCRYPT_MODE, key);
			}
			byte[] cipherText = cipher.doFinal(plainText.getBytes());
			return Base64.getEncoder().encodeToString(cipherText);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String basicEncrypt(String plainText) {
		return encrypt(plainText, IV_KEY);
	}

	public static String decrypt(String encryptedText, String iv) {
		Key key = new SecretKeySpec(Base64.getDecoder().decode(AES_KEY), "AES");
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			if (iv != null) {
				GCMParameterSpec gcm = new GCMParameterSpec(GCM_TAG_LENGTH, iv.getBytes());
				cipher.init(Cipher.DECRYPT_MODE, key, gcm);
			}
			else {
				cipher.init(Cipher.DECRYPT_MODE, key);
			}

			byte[] cipherText = cipher.doFinal((Base64.getDecoder().decode(encryptedText)));
			return new String(cipherText);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String basicDecrypt(String plainText) {
		return decrypt(plainText, IV_KEY);
	}

}
