package org.chirs.study.xml;

import java.io.File;

import org.chirs.study.xml.sax.SAXProcessor;

public class Main {
	
	public void tryout() {
		SAXProcessor processor = new SAXProcessor();
		processor.setup();
		processor.walkDocument(new File("SAML response.xml"));
	}
	
	public static void main(String[] args) {
		new Main().tryout();
	}
}
