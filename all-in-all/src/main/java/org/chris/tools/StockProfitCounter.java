package org.chris.tools;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockProfitCounter {
	
	private static final String S600340 = "华夏幸福 (600340)";
	private static final String S000018 = "中冠A (000018)";
	private static final String S600645 = "中原协和 (600645)"; 
	private static final String S600004 = "白云机场 (600004)"; 
	private static final String S300188 = "美亚柏科 (300188)";
	private static final String S000014 = "沙河股份 (000014)";
	private static final String S600000 = "浦发银行 (600000)";

	
	private static Map<String, double[]> stocks = new LinkedHashMap<String, double[]>();
	
	private static DecimalFormat decFormat = new DecimalFormat("####.##");
	
	public StockProfitCounter() {
		stocks.put(S600340, new double[] {47.430, 47.430});
		stocks.put(S000018, new double[] {25.646, 25.646, 25.646, 25.646});
		stocks.put(S600645, new double[] {58.142});
		stocks.put(S600004, new double[] {11.111, 11.111});
		stocks.put(S300188, new double[] {35.222, 35.222, 35.222, 35.222});
		// stocks.put(S000014, new double[] {});
		stocks.put(S600000, new double[] {14.167, 14.167, 14.875, 14.875});
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
		curPrices.put(S600340, 44.72);	// 华夏幸福 (600340)
		curPrices.put(S000018, 26.10);	// 中冠A (000018)
		curPrices.put(S600645, 61.04);	// 中原协和 (600645)
		curPrices.put(S600004, 11.34);	// 白云机场 (600004)
		curPrices.put(S300188, 35.62);	// 美亚柏科 (300188)
		// curPrices.put(S000014, 13.38);	// 沙河股份 (000014)
		curPrices.put(S600000, 14.10);	// 浦发银行 (600000)
		StockProfitCounter counter = new StockProfitCounter();
		counter.countProfilt(curPrices);
	}
}
