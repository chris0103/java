package org.chirs.study.algorithms.arena;

/**
 * Challenge: count the occurrence of specified character in given string.
 */
public class CharOccurrenceCounter {

	/**
	 * The easiest way, just cannot be more easy.
	 * @param str
	 * @param c
	 * @return
	 */
	public int solution1(String str, char c) {
		return str.length() - str.replace(String.valueOf(c), "").length();
	}
	
	public static void main(String[] args) {
		CharOccurrenceCounter counter = new CharOccurrenceCounter();
		String str = "a.b.c.d";
		int count = counter.solution1("a.b.c.d", '.');
		System.out.println("The occurrences of \".\" in string " + str + " is: " + count + ".");
	}
}
