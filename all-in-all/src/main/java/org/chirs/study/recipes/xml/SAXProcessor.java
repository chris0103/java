package org.chirs.study.recipes.xml;

import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.chirs.study.recipes.xml.exception.YoursBelovedCouldNotMakeItException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SAXProcessor implements XMLProcessor<InputStream> {
	
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
	private static final String PROPERTY_DOM_NODE = "dom-node";
	private static final String PROPERTY_LEXICAL_HANDLER = "lexical-handler";
	private static final String PROPERTY_XML_STR = "xml-string";
	
	
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
			showFeatures();
			
		} catch (ParserConfigurationException | SAXException e) {
			logger.error("Failed to get SAX parser.", e);
		}
	}

	@Override
	public void walkDocument(InputStream document) {
		
	}

	@Override
	public InputStream createDocument() {
		throw new YoursBelovedCouldNotMakeItException();
	}

	@Override
	public Object modifyDocument(InputStream document, String path, Object value) {
		return null;
	}
	
	private void showFeatures() {
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
			logger.debug("{}={}", PROPERTY_DECLARATION_HANDLER, reader.getFeature(PROPERTY_PERFIX + PROPERTY_DECLARATION_HANDLER));
			logger.debug("{}={}", PROPERTY_DOC_XML_VER, reader.getFeature(PROPERTY_PERFIX + PROPERTY_DOC_XML_VER));
			logger.debug("{}={}", PROPERTY_DOM_NODE, reader.getFeature(PROPERTY_PERFIX + PROPERTY_DOM_NODE));
			logger.debug("{}={}", PROPERTY_LEXICAL_HANDLER, reader.getFeature(PROPERTY_PERFIX + PROPERTY_LEXICAL_HANDLER));
			logger.debug("{}={}", PROPERTY_XML_STR, reader.getFeature(PROPERTY_PERFIX + PROPERTY_XML_STR));
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
}
