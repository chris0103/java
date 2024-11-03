package org.chris.study.readinglist.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "amazon")
public class AmazonProperties {

    private String associatedId;

    public void setAssociatedId(String associatedId) {
        this.associatedId = associatedId;
    }

    public String getAssociatedId() {
        return associatedId;
    }
}
