package crypto.quickstart;

import gnu.crypto.cipher.CipherFactory;
import gnu.crypto.cipher.IBlockCipher;
import gnu.crypto.mode.ModeFactory;

import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class OpeningShow {
	private IBlockCipher cipher;
	private byte[] plaintext = new byte[8];	
	private byte[] ciphertext = new byte[8];
	private byte[] key;
	
	public OpeningShow() {
		System.out.println("Here are the core concepts covered by the gnu-crypto:");
		System.out.println("Ciphers | Modes | Padding");
		System.out.println("Cascades and Assemblies");
		System.out.println("Message Digests | Message Authentication Code");
		System.out.println("Keypairs and Key Agreements | Signatures");
		System.out.println("Random Numbers");
		System.out.println();
	}
	
	public void showCiphers() {
		System.out.println("Supported Block ciphers:");
		Set<?> names = CipherFactory.getNames();
		for (Iterator<?> iter = names.iterator(); iter.hasNext(); ) {
			String name = (String)iter.next();
			System.out.print(name + " | ");
		}
		System.out.println();
	}
	
	public void showModes() {
		System.out.println("Supported modes:");
		Set<?> names = ModeFactory.getNames();
		for (Iterator<?> iter = names.iterator(); iter.hasNext(); ) {
			String name = (String)iter.next();
			System.out.print(name + " | ");
		}
	}
	
	public void demo() throws InvalidKeyException, IllegalStateException {
		System.out.println("\nTaking the DES block cipher for example:");
		plaintext = new byte[] {
			(byte)0x00, (byte)0x01, (byte)0x02, (byte)0x03,
			(byte)0x04, (byte)0x05, (byte)0x06, (byte)0x07
		};
		System.out.println("Plain Text:");
		for (byte b : plaintext) {
			System.out.print(String.format("0x%x", b) + " ");
		}
		System.out.println();
		key = new byte[] {
			(byte)0x08, (byte)0x09, (byte)0x0a, (byte)0x0b,
			(byte)0x0c, (byte)0x0d, (byte)0x0e, (byte)0x0f
		};
		System.out.println("Key:");
		for (byte b : key) {
			System.out.print(String.format("0x%x", b) + " ");
		}
		System.out.println();
		cipher = CipherFactory.getInstance("DES");
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put(IBlockCipher.CIPHER_BLOCK_SIZE, new Integer(8));
		attributes.put(IBlockCipher.KEY_MATERIAL, key);
		cipher.init(attributes);
		cipher.encryptBlock(plaintext, 0, ciphertext, 0);
		System.out.println("Encrypted Text:");
		for (byte b : ciphertext) {
			System.out.print(String.format("0x%x", b) + " ");
		}
		System.out.println();
		cipher.decryptBlock(ciphertext, 0, plaintext, 0);
		System.out.println("Decrypted Text:");
		for (byte b : plaintext) {
			System.out.print(String.format("0x%x", b) + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception {
		OpeningShow show = new OpeningShow();
		show.showCiphers();
		show .showModes();
		//show.demo();
	}
}
