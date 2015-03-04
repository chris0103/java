package org.chirs.tools;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockProfitCounter {
	
	private static final String S600340 = "华夏幸福 (600340)";
	private static final String S000018 = "中冠A (000018)";
	private static final String S600645 = "中原协和 (600645)"; 
	private static final String S000017 = "深中华A (000017)"; 
	private static final String S300188 = "美亚柏科 (300188)";
	private static final String S000014 = "沙河股份 (000014)";
	private static final String S600001 = "浦发银行 (600001)";

	
	private static Map<String, double[]> stocks = new LinkedHashMap<String, double[]>();
	
	private static DecimalFormat decFormat = new DecimalFormat("####.##");
	
	public StockProfitCounter() {
		stocks.put(S600340, new double[] {45.172, 45.172});
		stocks.put(S000018, new double[] {25.646, 25.646, 26.928, 26.928});
		stocks.put(S600645, new double[] {41.321, 41.321, 41.321, 41.321});
		// stocks.put(S000017, new double[] {7.519, 7.519, 7.895, 7.895});
		stocks.put(S300188, new double[] {33.544});
		stocks.put(S600001, new double[] {14.075, 14.075});
		stocks.put(S000014, new double[] {14.167, 14.167, 15.619, 15.619});
	}

	public void countProfilt(Map<String, Double> curPrices) {
		double totalProfit = 0.0;
		System.out.println("Portfolio profit:");
		for (String stock : stocks.keySet()) {
			double[] invests = stocks.get(stock);
			double curPrice = curPrices.get(stock);
			double profit = curPrice * invests.length;
			for (double invest : invests) {
				profit = profit - invest;
			}
			profit = profit * 100;
			System.out.println(stock + ":\t" + decFormat.format(profit));
			totalProfit = totalProfit + profit;
		}
		System.out.println("Total profit:\t" + decFormat.format(totalProfit));
	}
	
	public static void main(String[] args) {
		Map<String, Double> curPrices = new HashMap<String, Double>();
		curPrices.put(S600340, 46.57);	// 华夏幸福 (600340)
		curPrices.put(S000018, 25.01);	// 中冠A (000018)
		curPrices.put(S600645, 43.22);	// 中原协和 (600645)
		// curPrices.put(S000017, 7.77);	// 深中华A (000017)
		curPrices.put(S300188, 33.59);	// 美亚柏科 (300188)
		curPrices.put(S000014, 13.38);	// 沙河股份 (000014)
		curPrices.put(S600001, 14.09);	// 浦发银行 (600001)
		StockProfitCounter counter = new StockProfitCounter();
		counter.countProfilt(curPrices);
	}
}
