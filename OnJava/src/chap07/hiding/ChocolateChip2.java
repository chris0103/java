package chap07.hiding;

import chap07.hiding.cookie2.Cookie;

// Can't use package-access member from another package
public class ChocolateChip2 extends Cookie {

    public ChocolateChip2() {
        System.out.println("ChocolateChip2 constructor");
    }

    public void chomp() {
        bite(); // Protected method
    }

    public static void main(String[] args) {
        ChocolateChip2 x = new ChocolateChip2();
        x.chomp();
    }
}
