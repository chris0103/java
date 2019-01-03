package com.akkademy.messages;

import java.io.Serializable;

public class Delete implements Serializable {
	
    private final String key;

	public Delete(String key) {
        this.key = key;
    }
	
	public String getKey() {
		return key;
	}
}
