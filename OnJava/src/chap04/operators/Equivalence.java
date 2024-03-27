package chap04.operators;

public class Equivalence {

    static void show(String desc, Integer n1, Integer n2) {
        System.out.println(desc + ":");
        System.out.printf("%d==%d %b %b%n%n", n1, n2, n1 == n2, n1.equals(n2));
    }

    public static void test(int value) {
        // Automatic conversion to Integer: these are translated into calls to Integer.valueOf()
        Integer i1 = value;
        Integer i2 = value;
        show("Automatic", i1, i2);

        // Old way, deprecated in Java 9 and on:
        // Using standard new object-creation syntax: originally this was the preferred way to create "wrapped/boxed"
        // Integer objects
        Integer r1 = new Integer(value);
        Integer r2 = new Integer(value);
        show("new Integer()", r1, r2);

        // Preferred in Java 9 and on:
        // Starting with Java 9, valueOf() is preferred over new Integer() because it can cache values between -128
        Integer v1 = Integer.valueOf(value);
        Integer v2 = Integer.valueOf(value);
        show("Integer.valueOf()", v1, v2);

        // Primitives can't use equals():
        int x = value;
        int y = value;
        // x.equals(y); // Doesn't compile
        System.out.println("Primitive int:");
        System.out.printf("%d==%d %b%n%n", x, y, x == y);
    }

    public static void main(String[] args) {
        test(127);
        test(128);
    }
}
