package org.chris.quickstart;

import com.atomikos.logging.Logger;
import com.atomikos.logging.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {
    
    private static final Logger LOGGER = LoggerFactory.createLogger(App.class);
    
    private static final org.apache.log4j.Logger LOG4JLOGGER = org.apache.log4j.Logger.getLogger(App.class);
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        LOGGER.logWarning("Warning from atomikos");
        LOG4JLOGGER.warn("Warning from log4j");
    }
}

