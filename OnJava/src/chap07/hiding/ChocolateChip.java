package chap07.hiding;

import chap07.hiding.dessert.Cookie;

// Can't use package-access member from another package
public class ChocolateChip extends Cookie {

    public ChocolateChip() {
        System.out.println("ChocolateChip constructor");
    }

    public void chomp() {
        //- bite(); // Can't access bite
    }

    public static void main(String[] args) {
        ChocolateChip x = new ChocolateChip();
        x.chomp();
    }
}
