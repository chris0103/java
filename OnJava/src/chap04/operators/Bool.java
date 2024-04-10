package chap04.operators;

import java.util.Random;

// Relational and logical operators
public class Bool {

    public static void main(String[] args) {
        Random rand = new Random();
        int i = rand.nextInt(100);
        int j = rand.nextInt(100);
        System.out.println("i = " + i);
        System.out.println("j = " + j);
        System.out.println("i > j is " + (i > j));
        System.out.println("i < j is " + (i < j));
        System.out.println("i >= j is " + (i >= j));
        System.out.println("i <= j is " + (i <= j));
        System.out.println("i == j is " + (i == j));
        System.out.println("i != j is " + (i != j));

        // Treating an int as a boolean is not legal in Java:
        // System.out.println("i && j is " + (i && j));
        // System.out.println("i || j is " + (i || j));
        // System.out.println("!i is " + !i);

        System.out.println("(i < 10) && (j < 10) is " + ((i < 10) && (j < 10)));
        System.out.println("(i < 10) || (j < 10) is " + ((i < 10) || (j < 10)));
    }
}
