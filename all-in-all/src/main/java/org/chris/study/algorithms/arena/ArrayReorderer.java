package org.chris.study.algorithms.arena;

/**
 * Challenge: reorder an integer array so that all the odd numbers are before the even numbers.  
 */
public class ArrayReorderer {

	/**
	 * Having two indexes from the beginning and end of the array, move forward the beginning index until it encounters the first even number and move 
	 * the end index backward until it encounters the first odd number, then swap the two indexed number.
	 * @param array
	 */
	public void solution(int[] array) {
		int index = 0;
		int rIndex = array.length - 1;
		while (index < rIndex) {
			while (index < rIndex && array[index] % 2 == 1) {
				index++;
			}
			while (index < rIndex && array[rIndex] % 2 == 0) {
				rIndex--;
			}
			if (index < rIndex) {
				int temp = array[index];
				array[index] = array[rIndex];
				array[rIndex] = temp;
			}
		}
	}
	
	/*
	 * Prints array.
	 */
	private static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				System.out.print(", ");
			}
			System.out.print(array[i]);
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[] testArray = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		System.out.print("Original array: ");
		printArray(testArray);
		ArrayReorderer reorderer = new ArrayReorderer();
		reorderer.solution(testArray);
		System.out.print("Reordered array: ");
		printArray(testArray);
	}
	
}
