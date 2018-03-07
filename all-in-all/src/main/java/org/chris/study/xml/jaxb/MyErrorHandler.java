package org.chris.study.xml.jaxb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MyErrorHandler implements ErrorHandler {

    private final Logger logger = LoggerFactory.getLogger(MyErrorHandler.class);

    @Override
    public void error(SAXParseException e) throws SAXException {
        logger.error("Error parsing XML:", e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        logger.error("Fatal error parsing XML:", e);
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        logger.error("Warning parsing XML:", e);
    }
}
