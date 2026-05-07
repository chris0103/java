package chap06.housekeeping;

public class ForTypeInference {

    public static void main(String[] args) {
        for (var s : Spiciness.values()) {
            System.out.println(s);
        }
    }
}
