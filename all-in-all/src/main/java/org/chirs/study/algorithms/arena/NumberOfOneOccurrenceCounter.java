package org.chirs.study.algorithms.arena;

/**
 * Challenge: find the occurrences of number "1" from 1 to any given decimal positive number.
 */
public class NumberOfOneOccurrenceCounter {

	/**
	 * The most straightforward but inefficient way, to loop through all the numbers and count occurrence of number "1" over each iteration.
	 * @param num
	 * @return
	 */
	public int solution1(int num) {
		int count = 0;
		for (int i = 1; i <= num; i++) {
			count += countNumberOfOne(i);
		}
		return count;
	}
	
	/*
	 * Counts number of "1" for given number.
	 */
	private int countNumberOfOne(int num) {
		String numStr = String.valueOf(num);
		return numStr.length() - numStr.replace("1", "").length();
	}
	
	/**
	 * To divide the number into two parts, for example 21345 to [1, 1345] and [1346, 21345], with the latter size an exponent of 10. Then,<br />
	 * 1) count the occurrence of "1" at the topmost digit, if:<br />
	 * &nbsp;&nbsp;a. the given number is 1xxxx, the occurence will be "xxxx + 1"<br />
	 * &nbsp;&nbsp;b. the given number is nxxxx where n > 1, the occurrence will be 10000;<br />
	 * 2) count the occurrence of "1" at lower digits, that will be all the possible permutation calculated by <b>firstDigit * (num.length - 1) * power(10, (num.length - 2))</b>;<br />
	 * 3) send the first part ([1, 1345]) to recursion.
	 * @param num
	 * @return
	 */
	public int solution2(int num) {
		if (num <= 0) {
			return 0;
		}
		if (num > 0 && num <= 9) {
			return 1;
		}
		String numStr = String.valueOf(num);
		int topDigit = Integer.parseInt(numStr.substring(0, 1));
		int countForTopDigit = 0;
		if (topDigit > 1) {
			countForTopDigit = (int) Math.pow(10, numStr.length() - 1);
		} else if (topDigit == 1) {
			countForTopDigit = Integer.parseInt(numStr.substring(1)) + 1;
		}
		int countForLowerDigits = topDigit * (numStr.length() - 1) * (int) Math.pow(10, numStr.length() - 2);
		int countForRecursive = solution2(Integer.parseInt(numStr.substring(1)));
		return countForTopDigit + countForLowerDigits + countForRecursive;
	}
	
	public static void main(String[] args) {
		int number = 21345;
		NumberOfOneOccurrenceCounter counter = new NumberOfOneOccurrenceCounter();
		int count = counter.solution2(number);
		System.out.println("The occurrence of number one from 1 to " + number + " is " + count + ".");
	}
}
