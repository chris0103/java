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
        return itemsWithWeight.get(random.nextInt(itemsWithWeight.size()));
    }

    public String randomWithWeight(int count) {
        List<String> items = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            items.add(i + "");
        }
        return randomWithWeight(items);
    }

    public static void main(String[] args) {
        List<String> items = new ArrayList<>();
        items.add("1");
        items.add("2");
        items.add("3");
        String randomItem = new RandomPlus().randomWithWeight(10);
        System.out.println(randomItem);
    }
}
