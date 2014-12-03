package org.chirs.study.recipes.xml;

public class TheCook {

	public void cook() {
		XMLProcessor<?> processor = new SAXProcessor();
		processor.setup();
	}
	
	public static void main(String[] args) {
		new TheCook().cook();
	}
}
