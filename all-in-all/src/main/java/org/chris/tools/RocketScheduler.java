package org.chris.tools;

import java.util.ArrayList;
import java.util.List;

public class RocketScheduler {

	public void scheduleVideo() {
		int start = 17;
		int end = 480;
		int round = 4;
		int span = 33;
		
		List<Integer> schedules = new ArrayList<>();
		do {
			start++;
			schedules.add(start);
			if (start == end) {
				start = 0;
				round--;
			}
		} while (round > 0);
		
		int interval = schedules.size() / span;
		for (int schedule : schedules) {
			if (schedule % interval == 0) {
				System.out.println(schedule);
			}
		}
	}
	
	public static void main(String[] args) {
		RocketScheduler scheduler = new RocketScheduler();
		scheduler.scheduleVideo();
	}
}
