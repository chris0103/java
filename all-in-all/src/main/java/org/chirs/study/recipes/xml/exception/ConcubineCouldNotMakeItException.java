package org.chirs.study.recipes.xml.exception;

public class ConcubineCouldNotMakeItException extends UnsupportedOperationException {

	private static String msg = "Concubine could not make it!";
	
	public ConcubineCouldNotMakeItException() {
		super(msg);
	}

	public ConcubineCouldNotMakeItException(String message, Throwable e) {
		super(message, e);
	}

	public ConcubineCouldNotMakeItException(String message) {
		super(message);
	}

	public ConcubineCouldNotMakeItException(Throwable e) {
		super(msg, e);
	}
}
