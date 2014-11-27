package org.chirs.tools.crusade;

import java.util.StringTokenizer;

public class ClassMainInvoker {

    public int[] ip2IntArray(String host) {
        int[] address = {-1, -1, -1, -1};
        int i = 0;
        StringTokenizer tokens = new StringTokenizer(host, ".");
        if (tokens.countTokens() > 4) {
            return null;
        }
        while (tokens.hasMoreTokens()) {
            try {
                address[i++] = Integer.parseInt(tokens.nextToken()) & 0xFF;
            } catch(NumberFormatException e) {
                return null;
            }
        }
        return address;
     }
	
	public static void main(String[] args) {
		ClassMainInvoker invoker = new ClassMainInvoker();
		String address = "192.168.1.";
		int[] addr = invoker.ip2IntArray(address);
		for (int i = 0; i < addr.length; i++) {
			System.out.println(addr[i]);
		}
	}
}
