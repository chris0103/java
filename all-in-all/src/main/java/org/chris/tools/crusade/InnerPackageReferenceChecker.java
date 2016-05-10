package org.chris.tools.crusade;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class InnerPackageReferenceChecker {
	
	/*
	 * common-kernel
	 */
	private static String path = "C:\\chris\\sandbox\\workspace\\project\\src\\com\\ibm\\genelco\\";
	private static String currentPkg = "kernel\\present\\jsf\\template\\common\\";
	private static String project = "common-kernel\\";
	private static String clazz = "AbstractDataTransferObject.java";	
	
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
