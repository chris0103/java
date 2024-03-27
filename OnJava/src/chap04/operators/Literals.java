package chap04.operators;

public class Literals {

    public static void main(String[] args) {
        // Hexadecimal (lowercase)
        int i1 = 0x2f;
        System.out.println("i1: " + Integer.toBinaryString(i1));

        // Hexadecimal (uppercase)
        int i2 = 0X2F;
        System.out.println("i2: " + Integer.toBinaryString(i2));

        // Octal (leading zero)
        int i3 = 0177;
        System.out.println("i3: " + Integer.toBinaryString(i3));

        // max char hex value
        char c = 0xffff;
        System.out.println("c: " + Integer.toBinaryString(c));

        // max byte hex value
        byte b = 0x7f;
        System.out.println("b: " + Integer.toBinaryString(b));

        // max short hex value
        short s = 0x7fff;
        System.out.println("s: " + Integer.toBinaryString(s));

        long n1 = 200L; // long suffix
        long n2 = 200l; // long suffix (can be confusing)
        long n3 = 200;

        // Java 7 Binary Literals
        byte blb = (byte) 0b00110101;
        System.out.println("blb: " + Integer.toBinaryString(blb));
        short bls = (short) 0B0010111110101111;
        System.out.println("bls: " + Integer.toBinaryString(bls));
        int bli = 0b00101111101011111010111110101111;
        System.out.println("bli " + Integer.toBinaryString(bli));
        long bll = 0b00101111101011111010111110101111;
        System.out.println("bll: " + Long.toBinaryString(bll));

        float f1 = 1;
        float f2 = 1F; // float suffix
        float f3 = 1f; // float suffix
        double d1 = 1d; // double suffix
        double d2 = 1D; // double suffix

        // (Hex and Octal also work with long)
    }
}
