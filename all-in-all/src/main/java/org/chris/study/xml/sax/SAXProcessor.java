package org.chris.study.xml.sax;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.chris.study.xml.XMLProcessor;
import org.chris.study.xml.exception.YoursBelovedCouldNotMakeItException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SAXProcessor implements XMLProcessor<File> {
	
	private static final String FEATURE_PREFIX = "http://xml.org/sax/features/";
	private static final String FEATURE_NS = "namespaces";
	private static final String FEATURE_NS_PREFIX = "namespace-prefixes";
	private static final String FEATURE_VALIDATION = "validation";
	private static final String FEATURE_EXTERNAL_GENERAL_ENTITIES = "external-general-entities";
	private static final String FEATURE_STR_INTERNING = "string-interning";
	private static final String FEATURE_EXTERNAL_PARAM_ENTITIES = "external-parameter-entities";
	private static final String FEATURE_LEXICAL_HANDLER_PARAM_ENTITIES = "lexical-handler/parameter-entities";
	
	private static final String PROPERTY_PERFIX = "http://xml.org/sax/properties/";
	private static final String PROPERTY_DECLARATION_HANDLER = "declaration-handler";
	private static final String PROPERTY_DOC_XML_VER = "document-xml-version";
	private static final String PROPERTY_LEXICAL_HANDLER = "lexical-handler";
	
	private final Logger logger = LoggerFactory.getLogger(SAXProcessor.class);

	private SAXParser parser;

	@Override
	public void setup() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setValidating(true);
		try {
			parser = factory.newSAXParser();
			logger.debug("SAX parser created: {}", parser);
			showFeaturesAndProperties();
		} catch (ParserConfigurationException | SAXException e) {
			logger.error("Failed to get SAX parser.", e);
		}
	}

	@Override
	public void walkDocument(File document) {
		logger.debug("Starting parsing of document {}", document);
		SAXHandler handler = new SAXHandler();
		try {
			parser.parse(document, handler);
		} catch (SAXException e) {
			logger.error("Failed to parse document {}", document, e);
		} catch (IOException e) {
			logger.error("IOException occurred", e);
		}
	}

	@Override
	public File createDocument() {
		throw new YoursBelovedCouldNotMakeItException();
	}

	@Override
	public Object modifyDocument(File document, String path, Object value) {
		return null;
	}
	
	private void showFeaturesAndProperties() {
		XMLReader reader = null;
		try {
			reader = parser.getXMLReader();
			logger.debug("===SAX Parser Features===");
			logger.debug("{}={}", FEATURE_NS, reader.getFeature(FEATURE_PREFIX + FEATURE_NS));
			logger.debug("{}={}", FEATURE_NS_PREFIX, reader.getFeature(FEATURE_PREFIX + FEATURE_NS_PREFIX));
			logger.debug("{}={}", FEATURE_VALIDATION, reader.getFeature(FEATURE_PREFIX + FEATURE_VALIDATION));
			logger.debug("{}={}", FEATURE_EXTERNAL_GENERAL_ENTITIES, reader.getFeature(FEATURE_PREFIX + FEATURE_EXTERNAL_GENERAL_ENTITIES));
			logger.debug("{}={}", FEATURE_STR_INTERNING, reader.getFeature(FEATURE_PREFIX + FEATURE_STR_INTERNING));
			logger.debug("{}={}", FEATURE_EXTERNAL_PARAM_ENTITIES, reader.getFeature(FEATURE_PREFIX + FEATURE_EXTERNAL_PARAM_ENTITIES));
			logger.debug("{}={}", FEATURE_LEXICAL_HANDLER_PARAM_ENTITIES, reader.getFeature(FEATURE_PREFIX + FEATURE_LEXICAL_HANDLER_PARAM_ENTITIES));
			logger.debug("===SAX Parser Properties===");
			logger.debug("{}={}", PROPERTY_DECLARATION_HANDLER, reader.getProperty(PROPERTY_PERFIX + PROPERTY_DECLARATION_HANDLER));
			logger.debug("{}={}", PROPERTY_DOC_XML_VER, reader.getProperty(PROPERTY_PERFIX + PROPERTY_DOC_XML_VER));
			logger.debug("{}={}", PROPERTY_LEXICAL_HANDLER, reader.getProperty(PROPERTY_PERFIX + PROPERTY_LEXICAL_HANDLER));
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
}
