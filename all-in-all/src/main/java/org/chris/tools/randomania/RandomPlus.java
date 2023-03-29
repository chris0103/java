package org.chris.tools.randomania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPlus {

    private static Random random = new Random();

    public String randomWithWeight(List<String> items) {
        List<String> itemsWithWeight = new ArrayList<>();
        int size = items.size();
        System.out.println("Item size: " + size);
        for (int i = 0; i < size; i++) {
            for (int j = size; j > i; j--) {
                itemsWithWeight.add(items.get(i));
            }
        }
        printItems(itemsWithWeight);
        return itemsWithWeight.get(random.nextInt(itemsWithWeight.size()));
    }

    public String randomWithWeight(int count) {
        List<String> items = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            items.add(i + "");
        }
        return randomWithWeight(items);
    }
    
    private void printItems(List<String> items) {
    	for (String item : items) {
    		System.out.println(item);
    	}
    }

    public static void main(String[] args) {
        List<String> items = new ArrayList<>();
        items.add("Firefox Bookmarks");
        items.add("Zhihu Fav");
        items.add("Former Companies Arrangement");
        items.add("Learning Arrange");
        items.add("javalib");
        items.add("Luke's Songslist");
        items.add("Local -> Git");
        items.add("Writing");
        items.add("Duan She Li");
        items.add("Pillow Books");
        items.add("Reading");
        items.add("Games");
        items.add("New Songs");
        
        items.add("Computer Books scanner");
        items.add("Move tools Classes from tools project to all-in-all project");
        items.add("Practice");
        items.add("English Listening");
        items.add("手机容量清理");
        items.add("Canon Research");
        items.add("游戏整理");
        items.add("To Be Enhanced Movie");
        items.add("English Translation");
        items.add("Deutsch");
        items.add("Spider nets");
        
        String randomItem = new RandomPlus().randomWithWeight(items);
        System.out.println("\nThe chosen one: " + randomItem);
    }
}
