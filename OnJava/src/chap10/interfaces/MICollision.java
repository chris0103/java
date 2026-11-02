package chap10.interfaces;

interface Bob1 {
    default void bob() {
        System.out.println("Bob1::Bob");
    }
}

interface Bob2 {
    default void bob() {
        System.out.println("Bob2::Bob");
    }
}

// Multiple inheritance collision example
// class Bob implements Bob1, Bob2 { }

interface Sam1 {
    default void sam() {
        System.out.println("Sam1::Sam");
    }
}

interface Sam2 {
    default void sam(int i) {
        System.out.println("Sam2::Sam");
    }
}

// This works because the argument lists are distinct:
class Sam implements Sam1, Sam2 { }

interface Max1 {
    default void max() {
        System.out.println("Max1::Max");
    }
}

interface Max2 {
    default int max() {
        return 47;
    }
}

// Type Max1 and Max2 are incompatible: both define max(), but with unrelated return types
// class Max implements Max1, Max2 { }
