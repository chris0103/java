package org.chirs.tools;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class SystemInfo {
	
	public Font[] getAllFonts() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    return env.getAllFonts();
	}
	
	public void showMemoryUsage() {
		System.out.println("Memory usage: " + Runtime.getRuntime().freeMemory() + "/" + Runtime.getRuntime().totalMemory());
	}

	public static void main(String[] args) {
		SystemInfo sysInfo = new SystemInfo();
		Font[] fonts = sysInfo.getAllFonts();
		System.out.println("===System Font List===");
	    for (Font f : fonts) {
	      System.out.println(f.getFontName());
	    }
	    sysInfo.showMemoryUsage();
	}
}
