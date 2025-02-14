package chap08.reuse;

class Art {

    Art() {
        System.out.println("Art constructor");
    }
}

class Drawing extends Art {

    Drawing() {
        System.out.println("Drawing constructor");
    }
}

// Constructor calls during inheritance
public class Cartoon extends Drawing {

    public Cartoon() {
        System.out.println("Cartoon constructor");
    }

    public static void main(String[] args) {
        Cartoon x = new Cartoon();
    }
}
