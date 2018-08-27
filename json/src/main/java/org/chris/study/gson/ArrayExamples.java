package org.chris.study.gson;

import com.google.gson.Gson;

public class ArrayExamples {

	private static Gson gson = new Gson();

	public static void main(String[] args) {
        ArrayExamples ae = new ArrayExamples();
        System.out.println("Array Example:");
        ae.serialize();
        ae.deserialize();
    }

    public void deserialize() {
        System.out.println("Deserializing [1,2,3,4,5] ...");
        int[] ints = gson.fromJson("[1,2,3,4,5]", int[].class);
        for (int i : ints) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    public void serialize() {
        System.out.println("Serializing int array {1, 2, 3, 4, 5} ...");
        int[] ints = { 1, 2, 3, 4, 5 };
        System.out.println(gson.toJson(ints));     // ==> [1,2,3,4,5]
        System.out.println();
        System.out.println("Serializing string array { \"abc\", \"def\", \"ghi\" } ...");
        String[] strings = { "abc", "def", "ghi" };
        System.out.println(gson.toJson(strings));  // ==> ["abc", "def", "ghi"]
        System.out.println();
    }
}
