package org.chris.study.cookbook.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

public class CollectionOperations {

    public static void createList() {
        List<String> list1 = Arrays.asList(new String[] { "Apple", "Banana", "Citrus" });
        printList(list1);

        List<String> list2 = Arrays.asList("Apple", "Banana", "Citrus");
        printList(list2);

        List<String> list3 = new ArrayList<String>();
        Collections.addAll(list2, "Apple", "Banana", "Citrus");
        printList(list3);

        List<String> list4 = Lists.newArrayList("Apple", "Banana", "Citrus");
        printList(list4);
    }

    public static void main(String[] args) {
        createList();
    }

    private static void printList(List<String> list) {
        list.stream().forEach(System.out::println);
    }
}
