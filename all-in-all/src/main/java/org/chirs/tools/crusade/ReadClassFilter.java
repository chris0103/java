package org.chirs.tools.crusade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadClassFilter {

	private static String pathTemplate = "D:\\sandbox\\workspace\\project\\src\\main\\java\\";
	
	public List<String> findReadAbstractClass(File dir) throws IOException {
		List<String> ret = new ArrayList<String>();
		File[] subFiles = dir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				if (pathname.isDirectory()) {
					return true;
				}
				if (pathname != null && pathname.getName().endsWith(".java")) {
					return true;
				}
				return false;
			}
		});
		if (subFiles == null) {
			return ret;
		}
		for (File subFile : subFiles) {
			if (subFile.isFile()) {
				BufferedReader br = new BufferedReader(new FileReader(subFile));
				String line = null;
				boolean readFound = false;
				boolean keyFound = false;
				while ((line = br.readLine()) != null) {
					if (line.contains("@author Chris")) {
						readFound = true;
					}
					if (line.contains("Private constructors")) {
						keyFound = true;
					}
					if (readFound && keyFound) {
						ret.add(subFile.getName());
						break;
					}
				}
			} else {
				ret.addAll(findReadAbstractClass(subFile));
			}
		}
		return ret;
	}
	
	
	public static void main(String[] args) throws IOException {
		String[] projects = new String[] {
			"tif-utils_legacy", "tif-core_legacy"
		};
		List<String> readAbstractFiles = new ArrayList<String>();
		for (String project : projects) {
			String currentPath = pathTemplate.replace("project", project);
			File root = new File(currentPath);
			readAbstractFiles.addAll(new ReadClassFilter().findReadAbstractClass(root));
		}
		for (String file : readAbstractFiles) {
			System.out.println(file);
		}
	}
}
