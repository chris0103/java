package org.chris.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReadingScheduler {

	private static SimpleDateFormat format = new SimpleDateFormat("MM-dd");
	
	public void scheduleReading(String title, int pages, int quota) {
		System.out.println("Reading schedule for " + title);
		int days = Math.round(((float) pages) / ((float) quota));
		int progress = 0;
		Calendar schedule = Calendar.getInstance();
		schedule.roll(Calendar.DAY_OF_WEEK, false);
		while (progress < pages) {
			schedule.roll(Calendar.DAY_OF_YEAR, true);
			if (schedule.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY || schedule.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				continue;
			}
			progress += quota;
			if (progress > pages) {
				progress = pages;
			}
			System.out.println(format.format(schedule.getTime()) + ":\t" + progress + "\t\t[ ]");
		}
		System.out.println("Totally " + days + " days.");
	}
	
	public static void main(String[] args) {
		ReadingScheduler scheduler = new ReadingScheduler();
		scheduler.scheduleReading("Apache MyFaces 1.2 Web Application Development", 409, 5);
	}
}
