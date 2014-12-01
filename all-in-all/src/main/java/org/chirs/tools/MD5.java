package org.chirs.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	public byte[] digest(String message) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] digest = md.digest(message.getBytes());
		return digest;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		MD5 md5 = new MD5();
		String message = "www.renren.com";
		byte[] digest = md5.digest(message);
		StringBuffer buf = new StringBuffer();
		for (byte b : digest) {
			buf.append(String.format("%02x", b));
		}
		System.out.println("Digest for " + message + ":");
		System.out.println(buf.toString());
	}
}
