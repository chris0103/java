package org.chris.tools.randomania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPlus {

    private static Random random = new Random();

    public String randomWithWeight(List<String> items) {
        List<String> itemsWithWeight = new ArrayList<>();
        int size = items.size();
        for (int i = 0; i < size; i++) {
            for (int j = size; j > i; j--) {
                itemsWithWeight.add(items.get(i));
            }
        }
        for (String item : itemsWithWeight) {
            System.out.println(item);
        }
        return itemsWithWeight.get(random.nextInt(itemsWithWeight.size()));
    }

    public static void main(String[] args) {
        List<String> items = new ArrayList<>();
        items.add("one");
        items.add("two");
        items.add("three");
        String randomItem = new RandomPlus().randomWithWeight(items);
        System.out.println(randomItem);
    }
}
