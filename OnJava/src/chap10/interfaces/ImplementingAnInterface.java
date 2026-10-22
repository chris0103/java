package chap10.interfaces;

interface Concept { // package access
    void idea1();
    void idea2();
}

class Implementation implements Concept {

    @Override
    public void idea1() {
        System.out.println("idea1");
    }

    @Override
    public void idea2() {
        System.out.println("idea2");
    }
}
