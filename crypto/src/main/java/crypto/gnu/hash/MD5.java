package crypto.gnu.hash;

import gnu.crypto.hash.HashFactory;
import gnu.crypto.hash.IMessageDigest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import crypto.util.ByteUtils;

public class MD5 {

	private IMessageDigest md;
	private byte[] msg;	
	private byte[] digest;
	public MD5(){
		System.out.println("An example creating the message digest.");
	}
	
	public void demo() throws IOException {
		md = HashFactory.getInstance("MD5");
		File file = new File("test.txt");
		msg = new byte[(int)file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(msg);
		ByteUtils.printBytesInHex("file content", msg);
		md.update(msg, 0, msg.length);
		digest = md.digest();
		ByteUtils.printBytesInHex("digest", digest);
	}
	
	public static void main(String[] args) {
		try {
			MD5 md5 = new MD5();
			md5.demo();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
