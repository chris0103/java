package org.chirs.tools.random;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import com.javadocmd.simplelatlng.LatLng;

public class RandomHistoryLocator {

	private static Random random = new Random();
	private static SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
	
	private long timeScope = (long) (1000 * 86400 * 365.25 * 10000);	// 10000 years
	
	public void randomLocationAndTime() {
		LatLng location = LatLng.random();
		long historyTimestamp = System.currentTimeMillis() - timeScope + (long) (random.nextDouble() * timeScope);
		Calendar history = new GregorianCalendar();
		history.setTimeInMillis(historyTimestamp);
		String dateStr = format.format(history.getTime());
		System.out.println("Time: " + dateStr + ", location: (" + decimalToDMS(location.getLongitude(), true) + ", " + decimalToDMS(location.getLatitude(), false) + ")");
	}
	
	private String decimalToDMS(double coord, boolean isLongtitude) {
	    String output, degrees, minutes, seconds;
	    
	    String suffix = "";
	    if (isLongtitude) {
	    	if (coord > 0) {
	    		suffix = "E";
	    	} else {
	    		suffix = "W";
	    	}
	    } else {
	    	if (coord > 0) {
	    		suffix = "N";
	    	} else {
	    		suffix = "S";
	    	}
	    }

	    // gets the modulus the coordinate divided by one (MOD1).
	    // in other words gets all the numbers after the decimal point.
	    // e.g. mod = 87.728056 % 1 == 0.728056
	    //
	    // next get the integer part of the coord. On other words the whole number part.
	    // e.g. intPart = 87

	    double mod = coord % 1;
	    int intPart = (int)coord;

	    //set degrees to the value of intPart
	    //e.g. degrees = "87"

	    degrees = String.valueOf(Math.abs(intPart));

	    // next times the MOD1 of degrees by 60 so we can find the integer part for minutes.
	    // get the MOD1 of the new coord to find the numbers after the decimal point.
	    // e.g. coord = 0.728056 * 60 == 43.68336
	    //      mod = 43.68336 % 1 == 0.68336
	    //
	    // next get the value of the integer part of the coord.
	    // e.g. intPart = 43

	    coord = mod * 60;
	    mod = coord % 1;
	    intPart = (int)coord;

	    // set minutes to the value of intPart.
	    // e.g. minutes = "43"
	    minutes = String.valueOf(Math.abs(intPart));

	    //do the same again for minutes
	    //e.g. coord = 0.68336 * 60 == 41.0016
	    //e.g. intPart = 41
	    coord = mod * 60;
	    intPart = (int)coord;

	    // set seconds to the value of intPart.
	    // e.g. seconds = "41"
	    seconds = String.valueOf(Math.abs(intPart));

	    // I used this format for android but you can change it 
	    // to return in whatever format you like
	    // e.g. output = "87/1,43/1,41/1"
	    // output = degrees + "/1," + minutes + "/1," + seconds + "/1";

	    output = degrees + "°" + minutes + "'" + seconds + "\"";
	    
	    output += suffix;

	    return output;
	}
	
	public static void main(String[] args) {
		RandomHistoryLocator locator = new RandomHistoryLocator();
		locator.randomLocationAndTime();
	}
}
