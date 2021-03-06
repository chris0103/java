package org.chris.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReadingScheduler {

	private static SimpleDateFormat format = new SimpleDateFormat("MM/dd");
	
	public void scheduleReading(String title, int start, int end, int quota) {
		System.out.println("Reading schedule for " + title);
		int pages = end - start + 1;
		int days = Math.round(((float) pages) / ((float) quota));
		int progress = start;
		Calendar schedule = Calendar.getInstance();
		// schedule.roll(Calendar.DAY_OF_YEAR, false);
		while (progress < end) {
			schedule.roll(Calendar.DAY_OF_YEAR, true);
			if (schedule.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || schedule.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				continue;
			}
			progress += quota;
			if (progress > end) {
				progress = end;
			}			
			System.out.print(format.format(schedule.getTime()) + "\t");
			System.out.println(progress);
		}
		System.out.println("Totally " + days + " days.");
	}
	
	public static void main(String[] args) {
		ReadingScheduler scheduler = new ReadingScheduler();
		scheduler.scheduleReading("Billboard", 0, 260, 1);
	}
}
