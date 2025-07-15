package chap08.reuse;

class WithFinals {

    // Identical to "private" alone:
    private final void f() {
        System.out.println("WithFinals.f()");
    }

    // Also automatically "final":
    private void g() {
        System.out.println("WithFinals.g()");
    }
}

// It only looks like you can override a private or private final method
public class FinalOverridingIllusion {
}
