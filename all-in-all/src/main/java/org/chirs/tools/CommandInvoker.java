package org.chirs.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandInvoker {

	private String command = "netstat -an";
	private File dir = null;
	
	public void invokeCommand() {
        String responsePart = null;
        StringBuffer response = new StringBuffer();
        Runtime rt = Runtime.getRuntime();
        Process process = null;
        String osname = System.getProperty("os.name").toLowerCase();
        if (osname.indexOf("windows") != -1) {
            command = "cmd /c " + command;	// Windows style command invoking
        }
        try {
	        if (dir == null) {
	            process = rt.exec(command, null);
	        } else {
	            process = rt.exec(command, null, dir);
	        }
	        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        while ((responsePart = in.readLine()) != null) {
	            response.append(responsePart).append("\n");
	        }
	        in.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        System.out.println(response.toString());
	}
	
	public static void main(String[] args) {
		new CommandInvoker().invokeCommand();
	}
}
