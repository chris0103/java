package org.chris.study.concurrency.examples.jcip.chap16;

import java.util.HashMap;
import java.util.Map;

public class SafeStates {

	private final Map<String, String> states;
	
	public SafeStates() {
		states = new HashMap<String, String>();
		states.put("alaska", "AK");
		states.put("alabama", "AL");
		states.put("wyoming", "WY");
	}
	
	public String getAbbreviations(String s) {
		return states.get(s);
	}
}
