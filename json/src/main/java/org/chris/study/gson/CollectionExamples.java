package org.chris.study.gson;

import java.util.Collection;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

public class CollectionExamples {

	private static Gson gson = new Gson();
	
    public static void main(String[] args) {
        CollectionExamples ce = new CollectionExamples();
        System.out.println("Collection Example:");
        System.out.println();
        ce.serialize();
        ce.deserialize();
    }

    public void deserialize() {

    }

    public void serialize() {
        Collection<Integer> ints = Lists.immutableList(1, 2, 3, 4, 5);
        System.out.println("Serializing Integer collection {1, 2, 3, 4, 5} ...");
    }
}
