package crypto.cert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;

import org.apache.log4j.Logger;

import crypto.util.ByteUtils;

public class Certificate {

	private static final Logger logger = Logger.getLogger(Certificate.class);
	
	public static void main(String[] args) {
		try {
			KeyStore ks = KeyStore.getInstance("PKCS12");	// JKS type
			FileInputStream fis = new FileInputStream("RGCA.p12");
			ks.load(fis,"shyfzx".toCharArray());
			PrivateKey pKey = (PrivateKey)ks.getKey("RGCA", "shyfzx".toCharArray());
			RSAPrivateKey rsaPKey = (RSAPrivateKey)pKey;
			System.out.println("Algorithm: " + rsaPKey.getAlgorithm());
			System.out.println("Format: " + rsaPKey.getFormat());
			ByteUtils.printBytesInHex("Encoded", rsaPKey.getEncoded());
			System.out.println("Modulus: " + rsaPKey.getModulus());
			System.out.println("Private Exponent: " + rsaPKey.getPrivateExponent());
		} catch (KeyStoreException kse) {
			logger.error(kse);
		} catch (FileNotFoundException fnfe) {
			logger.error(fnfe);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
