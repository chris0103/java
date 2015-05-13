package com.oozinoz.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProcessComposite extends ProcessComponent {

	private List<ProcessComponent> subprocesses = new ArrayList<ProcessComponent>();
	
	@Override
	public int getStepCount(Set<String> visited) {
		visited.add(name);
		int count = 0;
		for (ProcessComponent subproc : subprocesses) {
			if (!visited.contains(subproc.getName())) {
				count += subproc.getStepCount(visited);
			}
		}
		return count;
	}
}
