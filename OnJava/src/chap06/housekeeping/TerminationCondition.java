package chap06.housekeeping;

// Using finalize() to detect an object that
// hasn't been properly cleaned up

import onjava.Nap;

class Book {

    boolean checkedOut = false;

    Book(boolean checkOut) {
        checkedOut = checkOut;
    }

    void checkIn() {
        checkedOut = false;
    }
    
    @Override
    protected void finalize() {
        if (checkedOut) {
            System.out.println("Error: checked out");
            // Normally, you'll also do this:
            // super.finalize();    // Call the base-class version
        }
    }
}

public class TerminationCondition {

    public static void main(String[] args) {
        Book novel = new Book(true);
        // Proper cleanup:
        novel.checkIn();
        // Drop the reference forgot to clean up:
        new Book(true);
        // Force garbage collection & finalization:
        System.gc();
        new Nap(1); // One-second delay
    }

}
