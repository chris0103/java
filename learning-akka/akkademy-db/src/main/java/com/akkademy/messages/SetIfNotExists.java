package com.akkademy.messages;

import java.io.Serializable;

public class SetIfNotExists implements Serializable {

    private final String key;

    private final Object value;

    public SetIfNotExists(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "SetIfNotExists{" + "key='" + key + '\'' + ", value=" + value + '}';
    }
}
