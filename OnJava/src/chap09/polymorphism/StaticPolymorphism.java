package chap09.polymorphism;

class StaticSuper {

    public static String staticGet() {
        return "Base staticGet()";
    }

    public String dynamicGet() {
        return "Base dynamicGet()";
    }
}

class StaticSub extends StaticSuper {

    public static String staticGet() {
        return "Derived staticGet()";
    }

    @Override
    public String dynamicGet() {
        return "Derived dynamicGet()";
    }
}

// Static methods are not polymorphic
public class StaticPolymorphism {

    public static void main(String[] args) {
        StaticSuper sup = new StaticSub();  // Upcast
        System.out.println(StaticSuper.staticGet());
        System.out.println(sup.dynamicGet());
    }
}
