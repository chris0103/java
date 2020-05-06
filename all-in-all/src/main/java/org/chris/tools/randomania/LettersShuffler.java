package org.chris.tools.randomania;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LettersShuffler {
	
	public void shuffleLetters(char start, char end) {
		List<Character> letterList = new LinkedList<Character>();
		if (start > end) {
			return;
		}
		for (char letter = start; letter <= end; letter++) {
			letterList.add(letter);
		}
		Collections.shuffle(letterList);
		for (char c : letterList) {
			System.out.print(c + " ");
		}
	}
	
	public void shuffleNumbers(int start, int end) {
		List<Integer> numbers = new LinkedList<>();
		if (start > end) {
			return;
		}
		for (int letter = start; letter <= end; letter++) {
			numbers.add(letter);
		}
		Collections.shuffle(numbers);
		for (int c : numbers) {
			System.out.println(c);
		}
	}
}
