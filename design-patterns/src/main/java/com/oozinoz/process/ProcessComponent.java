package com.oozinoz.process;

import java.util.HashSet;
import java.util.Set;

public abstract class ProcessComponent {

	protected String name;
	
	public int getStepCount() {
		return getStepCount(new HashSet<String>());
	}
	
	public abstract int getStepCount(Set<String> visited);

	public String getName() {
		return name;
	}
}
