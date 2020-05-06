package org.chris.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Words scheduler.
 */
public class WordsScheduler {

    public static void main(String[] args) {
        char first = 'f';
        char second = 'a';
        char third = 'c';
        StringBuffer buf = new StringBuffer();
        List<String> heads = new ArrayList<>();
        for (; first <= 'z'; first++) {
        	for (; second <= 'z'; second++) {
        		for (; third <= 'z'; third++) {
        			String head = buf.append(first).append(second).append(third).toString();
        			heads.add(head);
        			buf.setLength(0);
        		}
        		third = 'a';
        	}
        	second = 'a';
        }
        
        for (int i = 0; i < heads.size(); i++) {
        	if (i % 19 == 0) {
        		System.out.println(heads.get(i));
        	}
        }
    }
}
