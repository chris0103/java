package org.chris.study.xml.sax;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
	
	private final Logger logger = LoggerFactory.getLogger(SAXHandler.class);
	
	@Override
	public void startDocument() throws SAXException {
		logger.debug("Starting parsing document...");
	}
	
	@Override
	public void endDocument() throws SAXException {
		logger.debug("Finished parsing document");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		logger.debug("Start element: local name = {}, qname = {}, uri = {}.", localName, qName, uri);
		int attrCount = attributes.getLength();
		if (attrCount > 0) {
			logger.debug("Attributes:");
			for (int i = 0; i < attributes.getLength(); i++) {
				logger.debug("  {}[{}] = {}", attributes.getQName(i), attributes.getType(i), attributes.getValue(i));
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		logger.debug("End element: local name = {}, qname = {}, uri = {}.", localName, qName, uri);
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content = new String(ch, start, length).trim();
		if (!content.isEmpty()) {
			logger.debug("Content: {}", new String(ch, start, length));
		}
	}
	
	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		logger.debug("Ignoring whitespace: {}", new String(ch, start, length));
	}
	
	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		logger.debug("Start \"{}\" namespace scope. URI = {}", prefix, uri);
	}
	
	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		logger.debug("End \"{}\" namespace scope.", prefix);
	}

	@Override
	public InputSource resolveEntity(String publicId, String systemId) throws IOException, SAXException {
		// TODO Auto-generated method stub
		return super.resolveEntity(publicId, systemId);
	}

	@Override
	public void notationDecl(String name, String publicId, String systemId) throws SAXException {
		// TODO Auto-generated method stub
		super.notationDecl(name, publicId, systemId);
	}

	@Override
	public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
		// TODO Auto-generated method stub
		super.unparsedEntityDecl(name, publicId, systemId, notationName);
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		super.setDocumentLocator(locator);
	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		// TODO Auto-generated method stub
		super.processingInstruction(target, data);
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		super.skippedEntity(name);
	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		super.warning(e);
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		super.error(e);
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		super.fatalError(e);
	}
}
