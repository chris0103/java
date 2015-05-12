package com.oozinoz.machine;

import java.util.List;

public class Machine extends MachineComponent {

	private boolean running = false;
	private List<Object> material;
	private List<Object> owners;
	
	@Override
	public int getMachineCount() {
		return 1;
	}

	@Override
	public List<Object> getMaterial() {
		return material;
	}

	@Override
	public List<Object> getOwners() {
		return owners;
	}

	@Override
	public boolean isCompletelyUp() {
		return !running;
	}

	@Override
	public boolean stopAll() {
		try {
			running = false;
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
