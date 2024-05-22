package chap06.housekeeping;

class Rock2 {
    Rock2(int i) {
        System.out.print("Rock " + i + " ");
    }
}

// Constructors can have parameters
public class SimpleConstructor2 {

    public static void main(String[] args) {
        for (int i = 0;i < 8; i++) {
            new Rock2(i);
        }
    }
}
