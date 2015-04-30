package org.chris.tools.crusade;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class InnerPackageReferenceChecker {
	
	/*
	 * tif-core
	 */
//	private static String path = "D:\\sandbox\\workspace\\project\\src\\main\\java\\com\\covisint\\";
//	private static String currentPkg = "commonreg2\\messaging\\web\\";
//	private static String project = "tif-core\\";
//	private static String clazz = "UserServiceCodeMessage.java";
	
	/*
	 * Password service
	 */
//	private static String path = "D:\\workspace\\project\\src\\main\\java\\com\\covisint\\idm\\password\\";
//	private static String currentPkg = "rest\\server\\services\\";
//	private static String project = "password-service\\rest-server\\";
//	private static String clazz = "PasswordPolicyServiceImpl.java";
	
	/*
	 * ID-Sync
	 */
//	private static String path = "D:\\sandbox\\workspace\\project\\src\\main\\java\\com\\covisint\\css\\sync\\";
//	private static String currentPkg = "master\\directory\\";
//	private static String project = "tif-idsync\\";
//	private static String clazz = "LDAPAccessor.java";
	
	/*
	 * tif-utils
	 */
	private static String path = "D:\\sandbox\\workspace\\project\\src\\main\\java\\com\\covisint\\";
	private static String currentPkg = "commonreg2\\autogrant\\engine\\";
	private static String project = "tif-core\\";
	private static String clazz = "AutoGrant.java";	
	
	public static void main(String[] args) throws IOException {
		System.out.println("===" + clazz + "===");
		path = path.replace("project", project) + currentPkg + clazz;
		File file = new File(path);
		FileChannel channel = new FileInputStream(file).getChannel();
		ByteBuffer bb = ByteBuffer.allocate((int)(channel.size()));
		channel.read(bb);
		String fileContent = new String(bb.array());
		String[] siblings = file.getParentFile().list();
		for (String sibling : siblings) {	
			if (sibling.indexOf(".java") == -1) {
				continue;
			}
			sibling = sibling.replace(".java", "");
			if (fileContent.indexOf(sibling) != -1) {
				System.out.println(sibling + " referred.");
			}
		}
		channel.close();
	}
}
