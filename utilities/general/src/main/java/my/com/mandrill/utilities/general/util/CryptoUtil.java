package my.com.mandrill.utilities.general.util;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.com.mandrill.utilities.general.constant.ErrorCodeGlobalEnum;
import my.com.mandrill.utilities.general.exception.BusinessException;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;

import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CryptoUtil {

	public static final String ALGORITHM = "Ed25519";

	private static final int KEY_SIZE_BYTES = 32; // 256 bits

	private static byte[] generateRandomBytes() {
		byte[] randomBytes = new byte[KEY_SIZE_BYTES];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(randomBytes);
		return randomBytes;
	}

	public static String generateSecretKey() {
		return Base64.getEncoder().encodeToString(generateRandomBytes());
	}

	public static SslContext createTrustSSLContext(InputStream keyStoreFile, String password)
			throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		keyStore.load(keyStoreFile, password.toCharArray());

		TrustManagerFactory keyManagerFactory = TrustManagerFactory
				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		keyManagerFactory.init(keyStore);

		return SslContextBuilder.forClient().trustManager(keyManagerFactory).build();
	}

	public static PublicKey getPublicKey(String key) {
		try {
			byte[] byteKey = Base64.getDecoder().decode(key.getBytes());
			SubjectPublicKeyInfo pubKeyInfo = new SubjectPublicKeyInfo(
					new AlgorithmIdentifier(EdECObjectIdentifiers.id_Ed25519), byteKey);
			X509EncodedKeySpec x509PublicKey = new X509EncodedKeySpec(pubKeyInfo.getEncoded());
			KeyFactory kf = KeyFactory.getInstance(ALGORITHM);
			return kf.generatePublic(x509PublicKey);
		}
		catch (Exception e) {
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			log.error("Error during creating Public Key : {}", e.getMessage());
			throw new BusinessException(ErrorCodeGlobalEnum.FAILED_TO_EXTRACT_PUBLIC_KEY);
		}
	}

	public static PublicKey getPublicKeyByX509(String key) {
		try {
			byte[] byteKey = Base64.getDecoder().decode(key.getBytes());
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteKey);
			KeyFactory kf = KeyFactory.getInstance(ALGORITHM);
			return kf.generatePublic(x509EncodedKeySpec);
		}
		catch (Exception e) {
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			log.error("Error during creating Public Key : {}", e.getMessage());
			throw new BusinessException(ErrorCodeGlobalEnum.FAILED_TO_EXTRACT_PUBLIC_KEY);
		}
	}

	public static boolean verifySignature(byte[] data, PublicKey publicKey, byte[] signedData) {
		try {
			Signature signature = Signature.getInstance(ALGORITHM);
			signature.initVerify(publicKey);
			signature.update(data);
			return signature.verify(signedData);
		}
		catch (Exception e) {
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			log.error("Error during verifying signature : {}", e.getMessage());
			throw new BusinessException(ErrorCodeGlobalEnum.FAILED_TO_VERIFY_SIGNATURE);
		}

	}

}
