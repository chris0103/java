package org.chris.study.algorithms.arena;

public class BinaryPeriodFinder {

    public int solution(int num) {
        int lowerLimit = 1;
        int upperLimit = 1000000000;
        assert num >= lowerLimit && num <= upperLimit;
        int maxBinaryCount = (int) Math.ceil((Math.log(upperLimit) / Math.log(2)));
        int[] binaries = new int[maxBinaryCount];
        
        // constructs the binary array reversely
        int length = 0;
        while (num > 0) {
            binaries[length] = num % 2;
            num /= 2;
            length++;
        }

        for (int i = length - 1; i >= 0; i--) {
            System.out.print(binaries[i]);
        }
        System.out.println();
        
        for (int period = 1; period <= length / 2; period++) {
            boolean found = true;
            for (int i = 0; i < length - period; ++i) {
                if (binaries[i] != binaries[i + period]) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return period;
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        BinaryPeriodFinder bpf = new BinaryPeriodFinder();
        for (int num = 100; num < 1000; num++) {
            int period = bpf.solution(num);
            System.out.println("Period = " + period);
        }
    }
}
