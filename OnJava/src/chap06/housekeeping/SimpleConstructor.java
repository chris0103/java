package chap06.housekeeping;

class Rock {
    Rock() {
        System.out.print("Rock ");
    }
}

// Demonstration of a simple constructor
public class SimpleConstructor {

    public static void main(String[] args) {
        for (int i = 0;i < 10; i++) {
            new Rock();
        }
    }
}
