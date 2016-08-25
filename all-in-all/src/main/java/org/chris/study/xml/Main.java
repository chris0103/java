package org.chris.study.xml;

import java.io.File;

import org.chris.study.xml.jaxb.JaxbProcessor;

public class Main {
	
	public void tryout() throws Exception {
		
		/*
		SAXProcessor processor = new SAXProcessor();
		processor.setup();
		processor.walkDocument(new File("SAML response.xml"));
		*/
		
		JaxbProcessor processor = new JaxbProcessor();
		processor.setup();
		processor.walkDocument(new File("countries.xml"));
	}
	
	public static void main(String[] args) throws Exception {
		new Main().tryout();
	}
}
