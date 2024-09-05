package chap06.housekeeping;


import java.util.Arrays;

// Array initialization
public class ArrayInit {

    public static void main(String[] args) {
        Integer[] a = {
            1, 2,
            3, // Autoboxing
        };

        Integer[] b = new Integer[] {
            1, 2,
            3, // Autoboxing
        };

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }
}
