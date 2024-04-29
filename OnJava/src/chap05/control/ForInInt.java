package chap05.control;

import static onjava.Range.*;

public class ForInInt {

    public static void main(String[] args) {
        for (int i : range(10))     // 0..9
            System.out.print(i + " ");
        System.out.println();

        for (int i : range(5, 10))     // 5..9
            System.out.print(i + " ");
        System.out.println();

        for (int i : range(5, 20, 3))   // 5..20 step 3
            System.out.print(i + " ");
        System.out.println();

        for (int i : range(20, 5, -3))  // Count down
            System.out.print(i + " ");
        System.out.println();
    }
}
