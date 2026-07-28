package chap09.polymorphism;

class Useful {
    public void f() {}
    public void g() {}
}

class MoreUseful extends Useful {
    @Override
    public void f() {}
    @Override
    public void g() {}

    public void u() {}
    public void v() {}
    public void w() {}
}

public class Reflect {

    public static void main(String[] args) {
        Useful[] x = {
            new Useful(), new MoreUseful()
        };
        x[0].f();
        x[1].g();
        //- x[1].u();   // method not found in Useful

        ((MoreUseful) x[1]).u();  // Downcast/Reflect
        ((MoreUseful) x[0]).u();  // Exception thrown
    }
}
