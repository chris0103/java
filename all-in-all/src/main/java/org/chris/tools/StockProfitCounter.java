package org.chris.tools;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockProfitCounter {
	
	private static final String S600340 = "华夏幸福 (600340)";
	private static final String S000018 = "中冠A (000018)";
	private static final String S600645 = "中原协和 (600645)"; 
	private static final String S600021 = "上海电力 (600021)"; 
	private static final String S300188 = "美亚柏科 (300188)";
	private static final String S600005 = "武钢股份 (600005)";
	private static final String S300002 = "神州泰岳 (300002)";

	
	private static Map<String, double[]> stocks = new LinkedHashMap<String, double[]>();
	
	private static DecimalFormat decFormat = new DecimalFormat("####.##");
	
	public StockProfitCounter() {
		stocks.put(S600340, new double[] {28.827, 28.827, 31.782, 31.782});
		stocks.put(S000018, new double[] {31.173, 31.173, 32.731, 32.731, 32.731, 32.731});
		stocks.put(S600645, new double[] {77.917});
		stocks.put(S600021, new double[] {23.645, 23.645, 23.645, 23.645, 24.827, 24.827, 24.827, 24.827, 26.069, 26.069, 26.069, 26.069});
		stocks.put(S300188, new double[] {60.242, 60.242});
		stocks.put(S600005, new double[] {5.509, 5.509, 6.074, 6.074});
		stocks.put(S300002, new double[] {32.617, 32.617, 34.248, 34.248});
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
		curPrices.put(S600340, 29.59);	// 华夏幸福 (600340)
		curPrices.put(S000018, 33.08);	// 中冠A (000018)
		curPrices.put(S600645, 79.34);	// 中原协和 (600645)
		curPrices.put(S600021, 23.81);	// 上海电力 (600021)
		curPrices.put(S300188, 63.47);	// 美亚柏科 (300188)
		curPrices.put(S600005, 5.75);	// 武钢股份 (600005)
		curPrices.put(S300002, 33.89);	// 神州泰岳 (300002)
		StockProfitCounter counter = new StockProfitCounter();
		counter.countProfilt(curPrices);
	}
}
