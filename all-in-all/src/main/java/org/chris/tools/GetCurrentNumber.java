package org.chris.tools;

public class GetCurrentNumber {

	/** Get the current line number.
	 * @return int - Current line number.
	 */
	public int getLineNumber() {
	    return Thread.currentThread().getStackTrace()[2].getLineNumber();
	}
	
	public static void main(String[] args) {
		System.out.println(new GetCurrentNumber().getLineNumber());
	}
}
