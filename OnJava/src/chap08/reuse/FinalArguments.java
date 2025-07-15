package chap08.reuse;

class Gizmo {

    public void spin() {}
}

// Using "final" with method arguments
public class FinalArguments {

    void with(final Gizmo g) {
        //- g = new Gizmo();    // Illegal -- g is final
    }

    void without(Gizmo g) {
        g = new Gizmo();    // OK -- is not final
        g.spin();
    }

    //- void f(final int i) { i++; }    // Can't change

    // You can only read from a final primitive:
    int g(final int i) { return i + i; }

    public static void main(String[] args) {
        FinalArguments bf = new FinalArguments();
        bf.without(null);
        bf.with(null);
    }
}
