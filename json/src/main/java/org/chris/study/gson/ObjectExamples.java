package org.chris.study.gson;

import com.google.gson.Gson;

class BagOfPrimitives {

    private int value1 = 1;

    private String value2 = "abc";

    private transient int value3 = 3;

    BagOfPrimitives() {

    }

    @Override
    public String toString() {
        return "value1 = " + value1 + ", value2 = " + value2 + ", value3 = " + value3;
    }
}

public class ObjectExamples {

    public static void main(String[] args) {
        ObjectExamples oe = new ObjectExamples();
        System.out.println("Object Example:");
        System.out.println();
        oe.serialize();
        oe.deserialize();
    }

    public void deserialize() {
        Gson gson = new Gson();
        System.out.println("Deserializing object of BagOfPrimitives from {\"value1\":1,\"value2\":\"abc\"} ...");
        BagOfPrimitives obj = gson.fromJson("{\"value1\":1,\"value2\":\"abc\"}", BagOfPrimitives.class);
        System.out.println(obj);
        System.out.println();
    }

    public void serialize() {
        BagOfPrimitives obj = new BagOfPrimitives();
        Gson gson = new Gson();
        System.out.println("Serializing object of BagOfPrimitives [" + obj + "] ...");
        String json = gson.toJson(obj);
        System.out.println(json);
        System.out.println();
    }
}
