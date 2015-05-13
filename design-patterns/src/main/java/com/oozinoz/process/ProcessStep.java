package com.oozinoz.process;

import java.util.Set;

public class ProcessStep extends ProcessComponent {

	@Override
	public int getStepCount(Set<String> visited) {
		visited.add(name);
		return 1;
	}
}
