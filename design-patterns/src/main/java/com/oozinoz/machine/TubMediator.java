package com.oozinoz.machine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TubMediator {
	
	protected Map<Tub, Machine> tubToMachine = new HashMap<Tub, Machine>();

	public Machine getMachine(Tub tub) {
		return tubToMachine.get(tub);
	}
	
	public Set<Tub> getTubs(Machine machine) {
		Set<Tub> tubs = new HashSet<Tub>();
		for (Tub tub : tubToMachine.keySet()) {
			if (tubToMachine.get(tub).equals(machine)) {
				tubs.add(tub);
			}
		}
		return tubs;
	}

	public void set(Tub tub, Machine machine) {
		tubToMachine.put(tub, machine);
	}
}
