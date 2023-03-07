package org.chris.tools;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Biblioholism {
	
	private static DecimalFormat decFormat = new DecimalFormat("####.##");

	/**
	 * Find the most suitable selection of books to fit a "XXX off for every YYY" discount.
	 * @param prices
	 */
	public void buyBuyBuy(float[] prices, int threshold) {
		Map<String, Float> bills = new HashMap<>();
		for (int count = 1; count <= prices.length; count++) {
			float[] combos = new float[count];
			combination(prices, count, bills, combos, prices.length - 1);
		}
		for (String key : bills.keySet()) {
			float cost = bills.get(key);
			float odds = findOdds(cost, threshold);
			if (cost > 1000.0f) {
				continue;
			}
			if (odds > 0.2) {
				continue;
			}
			System.out.println(key + " = " + cost + "\t Odds = " + decFormat.format(odds));
		}
	}
	
	private void combination(float[] prices, int count, Map<String, Float> bills, float[] combos, int endIndex) {
		if (count <= 0) {
			return;
		}
		int pivot = count - 1;
		for (int i = endIndex; i >= pivot; i--) {
			combos[pivot] = prices[i];
			if (pivot == 0) {
				/*
				for(float combo : combos) {
					System.out.print(combo + "\t");
				}
				System.out.println();
				*/
				String key = String.valueOf(combos[0]);
				float cost = combos[0];
				for (int j = 1; j < combos.length; j++) {
					key += " + " + String.valueOf(combos[j]);
					cost += combos[j];
				}
				bills.put(key, cost);
			}
			combination(prices, pivot, bills, combos, i - 1);
		}
	}
	
	private float findOdds(float value, int threshold) {
		int floor = (int) (value / threshold) * threshold;
		return value - floor;
	}
	
	public static void main(String[] args) {
		float[] prices = new float[] {184.3f, 24.8f, 55.0f, 100.0f, 925.8f, 67.3f, 46.1f, 49.0f, 35.0f, 45.0f, 68.0f, 79f, 56.0f, 35.0f, 68.0f, 45.0f};
		new Biblioholism().buyBuyBuy(prices, 100);
	}
	
}
