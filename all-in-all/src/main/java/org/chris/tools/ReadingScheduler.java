package org.chris.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReadingScheduler {

	private static SimpleDateFormat format = new SimpleDateFormat("MM-dd");
	
	public void scheduleReading(String title, int pageStart, int pageEnd, int quota, boolean atWork) {
	    int pages = pageEnd - pageStart + 1;
		System.out.println("Reading schedule for " + title);
		int days = Math.round(((float) pages) / ((float) quota));
		int progress = pageStart - 1;
		Calendar schedule = Calendar.getInstance();
		schedule.roll(Calendar.DAY_OF_WEEK, false);
		while (progress < pageEnd) {
			schedule.roll(Calendar.DAY_OF_YEAR, true);
			if (atWork) {
			    if (schedule.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || schedule.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			        continue;
			    }
			} else {
	            if (schedule.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY || schedule.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
	                continue;
	            }
			}
			progress += quota;
			if (progress > pageEnd) {
				progress = pageEnd;
			}
			System.out.println(format.format(schedule.getTime()) + ":\t" + progress + "\t\t[ ]");
		}
		System.out.println("Totally " + days + " days.");
	}
	
	public static void main(String[] args) {
	    String title = "Apache MyFaces 1.2 Web Application Development";
		ReadingScheduler scheduler = new ReadingScheduler();
		scheduler.scheduleReading(title, 303, 409, 3, false);
	}
}
