package org.chirs.study.concurrency.examples.jcip.chap03;

/**
 * Listing 3.6. Allowing internal mutable state to escape. Don’t do this.
 */
public class UnsafeStates {

	// the states meant to be immutably defined
	private String[] states = new String[] {
			"AK", "AL", // ...
	};
	
	public String[] getStates() {
		return states;
	}
}
