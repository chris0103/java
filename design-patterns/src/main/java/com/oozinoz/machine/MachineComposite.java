package com.oozinoz.machine;

import java.util.ArrayList;
import java.util.List;

public class MachineComposite extends MachineComponent {

	private List<MachineComponent> machines = new ArrayList<MachineComponent>();
	
	@Override
	public int getMachineCount() {
		int count = 0;
		for (MachineComponent machine : machines) {
			count += machine.getMachineCount();
		}
		return count;
	}

	@Override
	public List<Object> getMaterial() {
		List<Object> materialForAll = new ArrayList<Object>();
		for (MachineComponent machine : machines) {
			materialForAll.addAll(machine.getMaterial());
		}
		return materialForAll;
	}

	@Override
	public List<Object> getOwners() {
		List<Object> ownersForAll = new ArrayList<Object>();
		for (MachineComponent machine : machines) {
			ownersForAll.addAll(machine.getOwners());
		}
		return ownersForAll;
	}

	@Override
	public boolean isCompletelyUp() {
		for (MachineComponent machine : machines) {
			if (!machine.isCompletelyUp()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean stopAll() {
		boolean result = true;
		for (MachineComponent machine : machines) {
			result = result && machine.stopAll();
		}
		return result;
	}
}
