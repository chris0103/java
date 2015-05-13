package com.oozinoz.machine;

import java.util.List;
import java.util.Set;

public class Machine extends MachineComponent {

	private boolean running = false;
	private List<Object> material;
	private List<Object> owners;
	
	/**
	 * Create a machine with the given id.
	 * @param id the identity of this machine
	 */
    public Machine(int id) {
        super(id);
    }
	
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
	public boolean isTree(Set<MachineComponent> visited) {
		visited.add(this);
		return true;
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
