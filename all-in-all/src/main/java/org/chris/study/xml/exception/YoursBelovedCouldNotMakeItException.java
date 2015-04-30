package org.chris.study.xml.exception;

public class YoursBelovedCouldNotMakeItException extends UnsupportedOperationException {

	private static String msg = "臣妾做不到啊！";
	
	public YoursBelovedCouldNotMakeItException() {
		super(msg);
	}

	public YoursBelovedCouldNotMakeItException(String message, Throwable e) {
		super(message, e);
	}

	public YoursBelovedCouldNotMakeItException(String message) {
		super(message);
	}

	public YoursBelovedCouldNotMakeItException(Throwable e) {
		super(msg, e);
	}
}
