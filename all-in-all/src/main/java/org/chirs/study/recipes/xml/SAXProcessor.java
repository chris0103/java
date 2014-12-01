package org.chirs.study.recipes.xml;

import java.io.InputStream;

import org.chirs.study.recipes.xml.exception.ConcubineCouldNotMakeItException;

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
		throw new ConcubineCouldNotMakeItException();
	}

	@Override
	public Object modify(InputStream document, String path, Object value) {
		return null;
	}
}
