package chap06.housekeeping;

class Plumbus { }

public class TypeInference {

    void method() {

        // explicit type
        String hello1 = "Hello";

        // type inference
        var hello = "Hello";

        // also available for user defined type
        Plumbus pb1 = new Plumbus();
        var pb2 = new Plumbus();
    }

    // also available for static method
    static void staticMethod() {
        var hello = "Hello";
        var pb2 = new Plumbus();
    }
}

class NoInference {
    String field1 = "Field initialization";
    // var field2 = "Can't do this";

    void method() {
        // var nonInitializer; // No inference data
        // var aNull = null;   // Non inference data
    }

    /*
    var inferReturnType() {
        return "Can't infer return type";
    }
    */
}
