package org.chris.study.concurrency.jcip.examples.chap03;

import java.util.HashSet;
import java.util.Set;

/**
 * Listing 3.5. Publishing an object.
 */
public class KnownSecrets {
	
	public static Set<Secret> knownSecrets;
	
	public void initialize() {
		knownSecrets = new HashSet<Secret>();
	}
}

class Secret {
	
}
