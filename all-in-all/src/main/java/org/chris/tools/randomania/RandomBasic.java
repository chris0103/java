package org.chris.tools.randomania;

import java.util.Random;

public class RandomBasic {

    private static Random random = new Random();

    public static void main(String[] args) {
        RandomBasic random = new RandomBasic();
        System.out.println(random.randomNumber(18));
        // System.out.println(random.randomNumberBetween(745, 1460));
    }

    public int randomNumber(int scope) {
        return random.nextInt(scope) + 1;
    }

    public int randomNumberBetween(int start, int end) {
        return start + random.nextInt(end - start + 1);
    }
}
