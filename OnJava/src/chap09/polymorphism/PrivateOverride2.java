package chap09.polymorphism;

// Detecting a mistaken override using @Override
public class PrivateOverride2 {

    private void f() {
        System.out.println("private f()");
    }

    public static void main(String[] args) {
        PrivateOverride2 po = new Derived2();
        po.f();
    }
}

class Derived2 extends PrivateOverride2 {

    //- @Override
    public void f() {
        System.out.println("public f()");
    }
}
