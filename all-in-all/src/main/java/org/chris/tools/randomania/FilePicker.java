package org.chris.tools.randomania;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FilePicker {

	private static final String INDENT = "";

	private static String repoPath = "E:\\音乐\\full music\\kugoo download music";
	
	private static Random random = new Random();
	
	private static List<String> files = new ArrayList<String>();
	
	public static void pickFiles(File dir, String indent) throws IOException {
		System.out.println(indent + dir.getName());
		indent = "--" + indent;
		File[] subFiles = dir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				if (pathname.isDirectory() 
						&& (pathname.getName().endsWith("jing.fm") 
								|| pathname.getName().endsWith("examine")
								|| (pathname.getName().endsWith("new")
								|| (pathname.getName().endsWith("Temp"))))) {
					return false;
				}
				return true;
			}
			
		});
		if (subFiles == null) {
			return;
		}
		for (File subFile : subFiles) {
			if (subFile.isFile()) {
				System.out.println(indent + "|-" + subFile.getName());
				files.add(subFile.getAbsolutePath());
			} else {
				pickFiles(subFile, indent);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		File root = new File(repoPath);
		pickFiles(root, INDENT);
		int round = 7;
		System.out.println("The chosen ones are...");
		for (int i = 0; i < round; i++) {
			int index = random.nextInt(files.size());
			Collections.shuffle(files);
			System.out.println(files.get(index));
		}
	}
}
