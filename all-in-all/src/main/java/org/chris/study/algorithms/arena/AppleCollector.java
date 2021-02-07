package org.chris.study.algorithms.arena;

import java.util.ArrayList;
import java.util.List;

public class AppleCollector {

    public int collectApples(int[] A, int K, int L) {
        if (K + L > A.length) {
            return -1;  // there is not way to take two disjoint segments in this case
        }
        
        if (K + L == A.length) {
            int sum = 0;
            for (int apples : A) {
                sum += apples;
            }
            return sum; // there is only one way to take two disjoint segments in this case
        }
        
        // find the max disjoint segments
        int sum = 0;
        List<Integer> possibleKApples = new ArrayList<>();
        List<Integer> possibleLApples = new ArrayList<>();
        
        for (int i = 0; i <= A.length - K; i++) {
            int temp = 0;
            for (int j = i; j < i + K; j++) {
                temp += A[j];
            }
            System.out.println("[" + i + "-" + (i + K - 1) + "]:" + temp + ".");
            possibleKApples.add(temp);
        }
        System.out.println();
        
        for (int i = 0; i <= A.length - L; i++) {
            int temp = 0;
            for (int j = i; j < i + L; j++) {
                temp += A[j];
            }
            System.out.println("[" + i + "-" + (i + L - 1) + "]:" + temp + ".");
            possibleLApples.add(temp);
        }
        
        for (int i = 0; i < possibleKApples.size(); i++) {
            for (int j = 0; j < possibleLApples.size(); j++) {
                if (i + K < j || j + L < i) {   // not overlapping
                    sum = Math.max(sum, possibleKApples.get(i) + possibleLApples.get(j));
                }
            }
        }
        return sum % 100000007;
    }
    
    public int collectApples2(int[] A, int K, int L) {
        for (int i = 1; i < A.length; i++) {
            A[i] += A[i - 1];
        }
        printArray(A);
        int result = A[K + L - 1], kMax = A[K - 1], lMax = A[L - 1];
        for (int i = (K + L); i < A.length; i++) {
            kMax = Math.max(kMax, A[i - L] - A[i - K - L]);
            lMax = Math.max(lMax, A[i - K] - A[i - K - L]);
            result = Math.max(result, Math.max(kMax + A[i] - A[i - L], lMax + A[i] - A[i - K]));
        }
        return result;
    }
    
    private void printArray(int[] A) {
        for (int a : A) {
            System.out.print(a + "\t");
        }
        System.out.println();
    }
    
    
    public static void main(String[] args) {
        AppleCollector appleCollector = new AppleCollector();
        int[] testApples = new int[] {6, 1, 4, 6, 3, 2, 7, 4};
        int sum = appleCollector.collectApples2(testApples, 2, 3);
        System.out.println("Sum is: " + sum);
    }
}
