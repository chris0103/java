package chap08.reuse;

class Intruments {
    public void play() {}
    static void tune(Intruments i) {
        // ...
        i.play();
    }
}

// Inheritance and upcasting
// Wind objects are instruments because they have the same interface:
public class Wind extends Intruments {

    public static void main(String[] args) {
        Wind flute = new Wind();
        Intruments.tune(flute); // Upcasting
    }
}
