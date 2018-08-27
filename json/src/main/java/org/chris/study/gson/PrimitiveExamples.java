package org.chris.study.gson;

import com.google.gson.Gson;

public class PrimitiveExamples {

    private Gson gson = new Gson();

    public static void main(String[] args) {
        PrimitiveExamples pe = new PrimitiveExamples();
        System.out.println("Primitive Example:");
        System.out.println();
        pe.serialize();
        pe.deserialize();
    }

    public void deserialize() {
        System.out.println("Deserializing int 1 ...");
        int one = gson.fromJson("1", int.class);
        System.out.println(one);
        System.out.println();
        System.out.println("Deserializing Integer 1 ...");
        Integer iOne = gson.fromJson("1", Integer.class);
        System.out.println(iOne);
        System.out.println();
        System.out.println("Deserializing Long 1 ...");
        Long lOne = gson.fromJson("1", Long.class);
        System.out.println(lOne);
        System.out.println();
        System.out.println("Deserializing boolean false ...");
        Boolean f = gson.fromJson("false", Boolean.class);
        System.out.println(f);
        System.out.println();
        System.out.println("Deserializing string \"abc\" ...");
        String str = gson.fromJson("\"abc\"", String.class);
        System.out.println(str);
        System.out.println();
        System.out.println("Deserializing string arary {\"abc\"} ...");
        String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);
        for (String s : anotherStr) {
            System.out.println(s + "\t");
        }
        System.out.println();
    }

    public void serialize() {
        String str = "";
        System.out.println("Serializing int 1 ...");
        str = gson.toJson(1);             // ==> 1
        System.out.println(str);
        System.out.println();
        System.out.println("Serializing string \"abcd\" ...");
        str = gson.toJson("abcd");        // ==> "abcd"
        System.out.println(str);
        System.out.println();
        System.out.println("Serializing Long 10 ...");
        str = gson.toJson(new Long(10));  // ==> 10
        System.out.println(str);
        System.out.println();
        System.out.println("Serializing int array {1} ...");
        int[] values = { 1 };
        str = gson.toJson(values);        // ==> [1]
        System.out.println(str);
        System.out.println();
    }
}
