package org.chris.tools.crusade;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ClassNumCounter {

	private static int classCounts = 0;
	private static int readCounts = 0;
	private static int totalClassCounts = 0;
	private static int totalReadCounts = 0;
	private static String indent = "";
	private static String pathTemplate = "C:\\chris\\sandbox\\workspace\\project\\src\\com\\ibm\\genelco\\";
	private static PrintWriter pw;
	
	public void countClass(File dir, String indent) throws IOException {
		indent = "--" + indent;
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
		if (subFiles == null || subFiles.length == 0) {
			return;
		}
		pw.println(indent + dir.getName());
		for (File subFile : subFiles) {
			boolean print = true;
			if (subFile.isFile()) {
				classCounts++;
				totalClassCounts++;
				BufferedReader br = new BufferedReader(new FileReader(subFile));
				String line = null;
				while ((line = br.readLine()) != null) {
					if (line.contains("@author Chris")) {
						readCounts++;
						totalReadCounts++;
						print = false;
						break;
					}
				}
				if (print) {
					System.out.println(subFile.getName());
					pw.println(indent + "|-" + subFile.getName());
				}
			} else {
				countClass(subFile, indent);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		DecimalFormat numberFormat = new DecimalFormat("#.##");
		numberFormat.setRoundingMode(RoundingMode.HALF_UP);
		File outputFile = new File("class_count.txt");
		if (!outputFile.exists()) {
			outputFile.createNewFile();
		}
		pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(outputFile)));
		String[] projects = new String[] {
			"common-accounting", "common-agency", "common-bank", "common-client", 
			"common-cmc", "common-commission", "common-core", "common-dictionary", 
			"common-kernel", "common-security", "common-taxusa", "common-userdata", 
			"grouplic-contract", "grouplic-dao", "grouplic-entity", "grouplic-intepreter", 
			"grouplic-orm",	"grouplic-present", "grouplic-service", "grouplic-web",
		};
		for (String project : projects) {
			classCounts = 0;
			readCounts = 0;
			String currentPath = pathTemplate.replace("project", project);
			pw.println("===PROJECT " + project + "===");
			File root = new File(currentPath);
			ClassNumCounter counter = new ClassNumCounter();
			counter.countClass(root, indent);
			pw.println("Classes in project "+ project + ": " + classCounts + ", read " + readCounts + ". Progress: " + numberFormat.format(readCounts * 100.00 / classCounts) + "%");
			pw.println();
		}
		double totalReadRate = totalReadCounts * 1.0 / totalClassCounts;
		pw.println("Total classes: " + totalClassCounts + ", total read " + totalReadCounts + ". Total prgress: " + numberFormat.format(totalReadRate * 100) + "%");
		
		Calendar onBoard = Calendar.getInstance();
		onBoard.set(Calendar.YEAR, 2015);
		onBoard.set(Calendar.MONTH, Calendar.SEPTEMBER);
		onBoard.set(Calendar.DAY_OF_MONTH, 1);
		Calendar today = Calendar.getInstance();
		long days = (today.getTimeInMillis() - onBoard.getTimeInMillis()) / (1000 * 60 * 60 * 24) + 1;
		int daysToBeKing = (int) (days / totalReadRate) + 1;
		System.out.println(days + " days after embark, days to be king: " + daysToBeKing);
		onBoard.add(Calendar.DAY_OF_YEAR, daysToBeKing);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("The day to be king: " + dateFormat.format(onBoard.getTime()));
		pw.println("The day to be king: " + dateFormat.format(onBoard.getTime()));

		pw.flush();
		pw.close();
	}
}
