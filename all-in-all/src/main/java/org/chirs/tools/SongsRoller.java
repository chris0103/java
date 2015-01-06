package org.chirs.tools;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SongsRoller {

	private boolean verbose = false;
	
	public SongsRoller() {
		
	}
	
	public SongsRoller(boolean verbose) {
		this.verbose = verbose;
	}
	
	public int roll(int count) {
		if (count < 10) {
			if (verbose) {
				System.out.println(count);
			}
			return 0;
		}
		int i = 10;
		while (count >= i) {
			if (verbose) {
				System.out.print(i + ":\t");
			}
			for (int time = 0; time < 3; time++) {
				count--;
				if (verbose) {
					System.out.print(count);
					if (time < 2) {
						System.out.print("  ");
					}
				}
				if (count < i) {
					break;
				}
			}
			if (verbose) {
				System.out.println();
			}
			i += 10;
			
		}
		return count;
	}
	
	public int getTotalListenCount(int count) {
		int round = 1;
		int totalListened = 0;
		while (count > 0) {
			if (verbose) {
				System.out.println("===Round " + (round++) + "===");
			}
			totalListened += count;
			count = roll(count);
		}
		if (verbose) {
			System.out.println("Total listened: " + totalListened + ".");
		}
		return totalListened;
	}
	
	public void getBlockSizeStatistics() {
		DecimalFormat numberFormat = new DecimalFormat("#.##");
		numberFormat.setRoundingMode(RoundingMode.HALF_UP);
		for (int i = 100; i <= 2000; i = i + 50) {
			int totalListened = getTotalListenCount(i);
			System.out.println(i + "\t" + totalListened + "\t" + numberFormat.format((float) totalListened / i));
		}
	}
	
	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
	
	public static void main(String[] args) {
		SongsRoller roller = new SongsRoller(true);
		roller.getTotalListenCount(93);
	}
}
