package crypto.rationale;

public class EuclidExtended {

    /**
     * Find the inverse element of b in a GF(p).
     * Rudiment:
     *  Construct arrays conforming to the following relationship:
     *  1) mA1 + bA2 = A3 (1) ; mB1 + bB2 = B3 (2)
     *  2) T1 = A1 - QB1; T2 = A2 - QB2; T3 = A3 - QB3
     *  => mT1 + bT2 = T3 (3)
     *  and during the whole calculation, (1),(2) and (3) keep still.
     *  
     *  The operands b and m are functioned as the Euclid getGCD method, 
     *  in the form of B3 and A3. When B3 = 0 and A3 = 1, we get 
     *  gcd(m, b) = 1, so in the last round B3 = 1, then here goes:
     *    mB1 + bB2 = B3 => mB1 + bB2 = 1
     *  =>bB2 = 1 + m(-B1) => B2 * b = 1 (mod) m.
     *  Then B2 is the inverse element.
     * @param b
     * @param m
     * @return
     */
    private int findInverse(int b, int m) {
        assert m > b;
        int[] A = new int[] {1, 0, m};
        int[] B = new int[] {0, 1, b};
        int[] T = new int[3];
        while (B[2] != 1) {
            if (B[2] == 0) {
                return -1;
            }
            int Q = A[2] / B[2];
            T[0] = A[0] - Q * B[0];
            T[1] = A[1] - Q * B[1];
            T[2] = A[2] - Q * B[2];
            A[0] = B[0];
            A[1] = B[1];
            A[2] = B[2];
            B[0] = T[0];
            B[1] = T[1];
            B[2] = T[2];
        }
        return (B[1] + m) % m;
    }
    
    public static void main(String[] args) {
        EuclidExtended ex = new EuclidExtended();
        System.out.println("The inverse element of 5 in 8-modulo is " + ex.findInverse(5, 8));
        System.out.println("The inverse element of 550 in 1759-modulo is " + ex.findInverse(550, 1759));
    }
}
