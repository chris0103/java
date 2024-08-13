package chap06.housekeeping;

class Mug {
    Mug(int marker) {
        System.out.println("Mug(" + marker + ")");
    }
}

// Instance initialization
public class Mugs {

    Mug mug1;
    Mug mug2 = new Mug(2);

    {
        mug1 = new Mug(1);
        // mug2 = new Mug(2);
        System.out.println("mug1 & mug2 initialized");
    }

    Mugs() {
        System.out.println("Mugs()");
    }

    Mugs(int i) {
        System.out.println("Mugs(int)");
    }

    public static void main(String[] args) {
        System.out.println("Inside main()");
        new Mugs();
        System.out.println("new Mugs() completed");
        new Mugs(1);
        System.out.println("new Mugs1() completed");
    }
}
