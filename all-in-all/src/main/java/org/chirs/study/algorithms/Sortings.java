package org.chirs.study.algorithms;

import java.util.Arrays;
import java.util.LinkedList;


public class Sortings {

	private static final int SHOW_PROCEDURE = 1;
	
	@SuppressWarnings("unused")
	private static final int HIDE_PROCEDURE = 0;
	
	private static int round = 0;
	private static int showProcedure;
	
	/**
	 * Bubble sort.
	 * @param <T>
	 * @param data
	 * @return
	 */
	public static <T extends Comparable<? super T>> T[] bubbleSort(T[] data) {
		if (data == null) {
			return null;
		}
		for (int i = data.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				T a = data[j];
				T b = data[j + 1];
				if (a.compareTo(b) > 0) {
					data[j] = b;
					data[j + 1] = a;
					if (showProcedure == SHOW_PROCEDURE) {
						showData(data, ++round);
					}
				}
			}
		}
		return data;
	}
	
	/**
	 * Insertion sort.
	 * @param <T>
	 * @param data
	 * @param showProcedure
	 * @return
	 */
	public static <T extends Comparable<? super T>> T[] insertionSort(T[] data) {
		if (data == null) {
			return null;
		}
		round = 0;
		LinkedList<T> linkedData = new LinkedList<T>(Arrays.asList(data));
		for (int i = 1; i < linkedData.size(); i++) {
			T a = linkedData.get(i);
			for (int j = 0; j < i; j++) {
				T b = linkedData.get(j);
				if (a.compareTo(b) < 0) {
					linkedData.remove(i);
					linkedData.add(j, a);
					if (showProcedure == SHOW_PROCEDURE) {
						showData(linkedData, ++round);
					}
					break;
				}
			}
		}
		return linkedData.toArray(data);
	}
	
	/**
	 * Insertion sort with binary search.
	 * @param <T>
	 * @param data
	 * @param showProcedure
	 * @return
	 */
	public static <T extends Comparable<? super T>> T[] binaryInsertionSort(T[] data) {
		if (data == null) {
			return null;
		}
		round = 0;
		LinkedList<T> linkedData = new LinkedList<T>(Arrays.asList(data));
		for (int i = 1; i < linkedData.size(); i++) {
			T a = linkedData.get(i);
			int left = 0;
			int right = i - 1;
			int mid = left + (right - left) / 2;
			while (left <= right) {
				T b = linkedData.get(mid);
				if (a.compareTo(b) < 0) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
				mid = left + (right - left) / 2;
			}
			linkedData.remove(i);
			linkedData.add(mid, a);
			if (showProcedure == SHOW_PROCEDURE) {
				showData(linkedData, ++round);
			}
		}
		return linkedData.toArray(data);
	}
	
	/**
	 * Merge sort.
	 * @param <T>
	 * @param data
	 * @return
	 */
	public static <T extends Comparable<? super T>> T[] mergeSort(T[] data) {
		if (data == null) {
			return null;
		}
		round = 0;
		mergeSort(data, 0, data.length - 1);
		return null;
	}
	
	private static <T extends Comparable<? super T>> void mergeSort(T[] data, int startIndex, int endIndex) {
		if (startIndex < endIndex) {
			// to be continued...
		}
	}
	
	/**
	 * Quick sort.
	 * @param <T>
	 * @param data
	 * @param showProcedure
	 * @return
	 */
	public static <T extends Comparable<? super T>> T[] quickSort(T[] data) {
		if (data == null) {
			return null;
		}
		round = 0;
		quickSort(data, 0 , data.length - 1);
		return data;
	}
	
	/*
	 * Quick sort recursion method.
	 */
	private static <T extends Comparable<? super T>> void quickSort(T[] data, int startIndex, int endIndex) {
		if (startIndex < endIndex) {
			int pivotIndex = partition(data, startIndex, endIndex);
			quickSort(data, startIndex, pivotIndex - 1);
			quickSort(data, pivotIndex + 1, endIndex);
		}
	}
	
	/*
	 * Quick sort partition method.
	 */
	private static <T extends Comparable<? super T>> int partition(T[] data, int startIndex, int endIndex) {
		T pivot = data[endIndex];
		int i = startIndex - 1;
		for (int j = startIndex; j < endIndex; j++) {
			T a = data[j];
			if (a.compareTo(pivot) <= 0) {
				i++;
				data[j] = data[i];
				data[i] = a;
				if (showProcedure == SHOW_PROCEDURE) {
					showData(data, ++round);
				}
			}
		}
		data[endIndex] = data[i + 1];
		data[i + 1] = pivot;
		if (showProcedure == SHOW_PROCEDURE) {
			showData(data, ++round);
		}
		return i + 1;
	}
	
	/*
	 * method template		
	 */
	//	public static <T extends Comparable<? super T>> T[] xxxSort(T[] data) {
	//		if (data == null) {
	//			return null;
	//		}
	//		round = 0;
	//		return null;
	//	}
	//	
	
	private static <T> void showData(Iterable<T> data, int round) {
		if (round == 0) {
			System.out.print("Origin : ");
		} else {
			System.out.print("Round " + round + ": ");
		}
		for (T t : data) {
			System.out.print(t + "\t");
		}
		System.out.println();
	}
	
	private static <T> void showData(T[] data, int round) {
		if (round == 0) {
			System.out.print("Origin : ");
		} else {
			System.out.print("Round " + round + ": ");
		}
		for (T t : data) {
			System.out.print(t + "\t");
		}
		System.out.println();
	}
	
	/*
	 * Accessor methods.
	 */
	
	public static int getShowProcedure() {
		return showProcedure;
	}

	public static void setShowProcedure(int showProcedure) {
		Sortings.showProcedure = showProcedure;
	}
	
	public static void main(String[] args) {
		setShowProcedure(SHOW_PROCEDURE);
		Integer[] integers = new Integer[] {2, 8, 7, 1, 3, 5, 6, 4};
		showData(integers, 0);
		// integers = bubbleSort(integers);
		// integers = insertionSort(integers, SHOW_PROCEDURE);
		// integers = binaryInsertionSort(integers, SHOW_PROCEDURE);
		integers = quickSort(integers);
		System.out.print("Result : ");
		for (Integer i : integers) {
			System.out.print(i + "\t");
		}
	}
}
