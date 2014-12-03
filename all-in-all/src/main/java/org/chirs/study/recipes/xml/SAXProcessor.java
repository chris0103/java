package org.chirs.study.recipes.xml;

import java.io.InputStream;

import org.chirs.study.recipes.xml.exception.YoursBelovedCouldNotMakeItException;

public class SAXProcessor implements XMLProcessor<InputStream> {

	@Override
	public XMLProcessor<InputStream> setup() {
		return null;
	}

	@Override
	public void walk(InputStream document) {
		
	}

	@Override
	public InputStream create() {
		throw new YoursBelovedCouldNotMakeItException();
	}

	@Override
	public Object modify(InputStream document, String path, Object value) {
		return null;
	}
}
