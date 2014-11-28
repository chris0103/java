package org.chirs.tools;

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
		while (i <= count) {
			if (verbose) {
				System.out.print(i + ":\t");
			}
			for (int time = 0; time < 3; time++) {
				count--;
				if (verbose) {
					System.out.print(count);
					if (time < 2) {
						System.out.print("\t");
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
	
	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
	
	public static void main(String[] args) {
		SongsRoller roller = new SongsRoller(false);
//		for (int i = 1; i <= 300; i++) {
//			System.out.println(i + " - " + roller.getTotalListenCount(i));
//		}
		roller.setVerbose(true);
		roller.getTotalListenCount(100);
	}
}
