package crypto.rationale;

public class Euclid {
    
    private int findGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        int temp = a % b;
        while (temp != 0) {
            a = b;
            b = temp;
            temp = a % b;
        }
        return b;
    }
    
    public static void main(String[] args) {
        Euclid euclid = new Euclid();
        int x = 60, y = 0;
        int gcd = euclid.findGCD(x,y);
        System.out.println("The great common factor of " + x + " and " + y + " is " + gcd + ".");
    }
}
