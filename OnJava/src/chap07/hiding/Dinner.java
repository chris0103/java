package chap07.hiding;

import chap07.hiding.dessert.Cookie;

// Uses the library
public class Dinner {

    public static void main(String[] args) {
        Cookie x = new Cookie();
        //- x.bite(); // Can't access
    }
}
