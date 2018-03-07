package org.chris.study.xml.jaxb;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

public class MyValidationEventHandler implements ValidationEventHandler {

    @Override
    public boolean handleEvent(ValidationEvent event) {
        System.out.println("Error catched!!");
        System.out.println("Event Severity: " + event.getSeverity());
        System.out.println("Event: " + event.getMessage());
        System.out.println("Event Linked Exception: " + event.getLinkedException());
        // the locator contains much more information like line, column, etc.
        System.out.println("Event Locator: " + event.getLocator().getObject());
        return false;
    }
}
