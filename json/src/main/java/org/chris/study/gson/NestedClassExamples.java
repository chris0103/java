package org.chris.study.gson;

import com.google.gson.Gson;

class A {

    public String a = "a";

    class B {

        public String b = "b";

        public B() {

        }
    }

    static class C {

        public String c = "c";

        public C() {

        }
    }
}

public class NestedClassExamples {

    public static void main(String[] args) {
        NestedClassExamples nce = new NestedClassExamples();
        System.out.println("Nested Class Example:");
        System.out.println();
        nce.serialize();
    }

    public void serialize() {
        A a = new A();
        Gson gson = new Gson();
        System.out.println("Serializing class A ...");
        String json = gson.toJson(a);
        System.out.println(json);
        System.out.println();

        System.out.println("Serializing class A.C ...");
        A.C c = new A.C();
        json = gson.toJson(c);
        System.out.println(json);
        System.out.println();
    }
}
