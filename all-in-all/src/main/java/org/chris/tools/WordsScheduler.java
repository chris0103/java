package org.chris.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Words scheduler.
 */
public class WordsScheduler {
	
	private static final int GROUP_LENGTH = 3;
	private static final char[] BEGINS_FROM = new char[] {'f', 'a', 'c'};
	
	private List<String> heads = new ArrayList<>();

    public static void main(String[] args) {
//        char first = 'f';
//        char second = 'a';
//        char third = 'c';
//        StringBuffer buf = new StringBuffer();
//        List<String> heads = new ArrayList<>();
//        for (; first <= 'z'; first++) {
//        	for (; second <= 'z'; second++) {
//        		for (; third <= 'z'; third++) {
//        			String head = buf.append(first).append(second).append(third).toString();
//        			heads.add(head);
//        			buf.setLength(0);
//        		}
//        		third = 'a';
//        	}
//        	second = 'a';
//        }
//        
//        for (int i = 0; i < heads.size(); i++) {
//        	if (i % 19 == 0) {
//        		System.out.println(heads.get(i));
//        	}
//        }
    	
    	new WordsScheduler().generateHeads();
    }
    
    public void generateHeads() {
    	char[] result = new char[GROUP_LENGTH];
    	generateHeads(0, result);
    	
    	for (String head : heads) {
    		System.out.println(head);
    	}
    }
    
    private void generateHeads(int curDigit, char[] result) {
    	if (curDigit == GROUP_LENGTH) {
    		heads.add(String.valueOf(result));
    		return;
    	}
    	for (char i = 'a'; i <= 'z'; i++) {
    		result[curDigit] = i;
    		generateHeads(curDigit + 1, result);
    	}
    }
}
