package chap08.reuse;


class SmallBrain { }

final class Dinosaur {
    int i = 7;
    int j = 1;
    SmallBrain s = new SmallBrain();
    void f() {}
}

// error: Cannot extend final class 'Dinosaur'
//- class Further extends Dinosaur { }

// Making an entire class final
public class Jurassic {
    public static void main(String[] args) {
        Dinosaur n = new Dinosaur();
        n.f();
        n.i = 40;
        n.j++;
    }
}
