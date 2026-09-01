package chap10.interfaces;

abstract class Basic3 {
    int f() { return 111; }
    // No abstract methods
}

public class AbstractWithoutAbstracts {
    // Basic3 b3 = new Basic3();    // error: Basic3 is abstract; cannot be instantiated
}
