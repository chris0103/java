package org.chris.tools;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockProfitCounter {
	
	private static final String S600340 = "华夏幸福 (600340)";
	private static final String S000018 = "中冠A (000018)";
	private static final String S600022 = "山东钢铁 (600022)"; 
	private static final String S600021 = "上海电力 (600021)"; 
	private static final String S300188 = "美亚柏科 (300188)";
	private static final String S600005 = "武钢股份 (600005)";
	private static final String S300002 = "神州泰岳 (300002)";

	
	private static Map<String, double[]> stocks = new LinkedHashMap<String, double[]>();
	
	private static DecimalFormat decFormat = new DecimalFormat("####.##");
	
	public StockProfitCounter() {
		stocks.put(S600340, new double[] {30.268, 30.268});
		stocks.put(S000018, new double[] {50.777, 50.777, 50.777, 50.777, 50.777, 50.777, 50.777, 50.777});
		stocks.put(S600022, new double[] {5.095});
		stocks.put(S600021, new double[] {31.686, 31.686, 31.686, 31.686, 33.270, 33.270, 33.270, 33.270});
		stocks.put(S300188, new double[] {36.613, 36.613});
		stocks.put(S600005, new double[] {7.031, 7.031});
		stocks.put(S300002, new double[] {25.173, 25.173, 25.173});
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
		curPrices.put(S600340, 29.38);	// 华夏幸福 (600340)
		curPrices.put(S000018, 51.41);	// 中冠A (000018)
		curPrices.put(S600022, 5.30);	// 山东钢铁 (600022)
		curPrices.put(S600021, 32.50);	// 上海电力 (600021)
		curPrices.put(S300188, 37.15);	// 美亚柏科 (300188)
		curPrices.put(S600005, 6.88);	// 武钢股份 (600005)
		curPrices.put(S300002, 23.73);	// 神州泰岳 (300002)
		StockProfitCounter counter = new StockProfitCounter();
		counter.countProfilt(curPrices);
	}
}
