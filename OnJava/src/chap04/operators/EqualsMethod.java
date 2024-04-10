package chap04.operators;

class ValA {
    int i;
}

class ValB {

    int i;

    public boolean equals(Object o) {
        ValB rval = (ValB) o;   // Cast o be to a ValB
        return i == rval.i;
    }
}

// Default equals() does not compare contents
public class EqualsMethod {

    public static void main(String[] args) {
        ValA va1 = new ValA();
        ValA va2 = new ValA();
        va1.i = va2.i = 100;
        System.out.println(va1.equals(va2));
        ValB vb1 = new ValB();
        ValB vb2 = new ValB();
        vb1.i = vb2.i = 100;
        System.out.println(vb1.equals(vb2));
    }
}
