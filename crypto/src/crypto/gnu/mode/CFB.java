package crypto.gnu.mode;

import gnu.crypto.mode.IMode;
import gnu.crypto.mode.ModeFactory;

import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CFB {
	
	private IMode mode;
	private byte[] plaintext;	
	private byte[] ciphertext;
	private byte[] key;
	private byte[] IV = new byte[16];
	public CFB() {
		System.out.println("An example encrypting and decrypting the plaintext with the AES in CFB mode.");
	}
	
	public void demo() throws InvalidKeyException, IllegalStateException {
		mode = ModeFactory.getInstance("CFB", "AES", 16);
		plaintext = new byte[] {
			(byte)0x00, (byte)0x01, (byte)0x02, (byte)0x03,
			(byte)0x04, (byte)0x05, (byte)0x06, (byte)0x07,
			(byte)0x08, (byte)0x09, (byte)0x0a, (byte)0x0b,
			(byte)0x0c, (byte)0x0d, (byte)0x0e, (byte)0x0f
		};
		key = new byte[] {
			(byte)0x00, (byte)0x01, (byte)0x02, (byte)0x03,
			(byte)0x04, (byte)0x05, (byte)0x06, (byte)0x07,
			(byte)0x08, (byte)0x09, (byte)0x0a, (byte)0x0b,
			(byte)0x0c, (byte)0x0d, (byte)0x0e, (byte)0x0f
		};
		ciphertext = new byte[plaintext.length];
		Arrays.fill(IV,(byte)0x00);
		
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put(IMode.CIPHER_BLOCK_SIZE, new Integer(16));
		attributes.put(IMode.KEY_MATERIAL, key);
		attributes.put(IMode.STATE, new Integer(IMode.ENCRYPTION));
		attributes.put(IMode.IV, IV);
		mode.init(attributes);
		
		int bs = mode.currentBlockSize();
		for (int i = 0; i + bs <= plaintext.length; i += bs) {
			mode.update(plaintext, i, ciphertext, i);
		}
		System.out.println("Encrypted Text:");
		for (byte b : ciphertext) {
			System.out.print(String.format("0x%x", b) + " ");
		}
		System.out.println();

		mode.reset();
		attributes.put(IMode.STATE, IMode.DECRYPTION);
		mode.init(attributes);
		for (int i = 0; i + bs <= ciphertext.length; i += bs) {
			mode.update(ciphertext, i, plaintext, i);
		}
		System.out.println("Decrypted Text:");
		for (byte b : plaintext) {
			System.out.print(String.format("0x%x", b) + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception {
		CFB cfb = new CFB();
		cfb.demo();
	}
}
