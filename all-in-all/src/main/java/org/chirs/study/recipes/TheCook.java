package org.chirs.study.recipes;

import java.io.File;

import org.chirs.study.xml.sax.SAXProcessor;

public class TheCook {

	public void cook() {
		SAXProcessor processor = new SAXProcessor();
		processor.setup();
		processor.walkDocument(new File("SAML response.xml"));
	}
	
	public static void main(String[] args) {
		new TheCook().cook();
	}
}
