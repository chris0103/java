package experiments;

import static onjava.Range.*;

public class Workbench {

    public static void main(String[] args) {

        for (int i : range(10, 1, -1))     // 5..9
            System.out.print(i + " ");

    }
}
