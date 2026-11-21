package chap10.interfaces.music5;

import chap09.polymorphism.music.Note;

interface Instrument {

    // Compile-time constant:
    int VALUE = 5; // static & final

    default void play(Note n) { // Automatically public
        System.out.println(this + ".play() " + n);
    }
    default void adjust() {
        System.out.println("Ajusting " + this);
    }
}

class Wind implements Instrument {

    @Override
    public String toString() {
        return "Wind";
    }
}

class Percussion implements Instrument {

    @Override
    public String toString() {
        return "Percussion";
    }
}

class Stringed implements Instrument {

    @Override
    public String toString() {
        return "Stringed";
    }
}

class Brass extends Wind {

    @Override
    public String toString() {
        return "Brass";
    }
}

class Woodwind extends Wind {

    @Override
    public String toString() {
        return "Woodwind";
    }
}

public class Music5 {

    // Doesn't care about type, so new types added to the system will work right:
    static void tune(Instrument i) {
        // ...
        i.play(Note.MIDDLE_C);
    }

    static void tuneAll(Instrument[] e) {
        for (Instrument i : e) {
            tune(i);
        }
    }

    public static void main(String[] args) {
        // Upcasting during addition to the array:
        Instrument[] orchestra = {
            new Wind(),
            new Percussion(),
            new Stringed(),
            new Brass(),
            new Woodwind()
        };
        tuneAll(orchestra);
    }
}
