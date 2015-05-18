package com.oozinoz.machine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MachineComposite extends MachineComponent {

	private List<MachineComponent> machines = new ArrayList<MachineComponent>();
	
	/**
	 * Create a composite with the given id, with the supplied parent machine component, and containing the given components.
	 * @param id
	 * @param parent
	 * @param responsible
	 */
    public MachineComposite(int id, MachineComponent parent, Engineer responsible) {
        super(id, parent, responsible);
    }
	
	/**
	 * Create a composite with the given id and with the supplied parent machine component.
	 * @param id
	 * @param parent
	 */
	public MachineComposite(int id, MachineComponent parent) {
        this(id, parent, (Engineer) null);
    }
	
	/**
	 * Create a composite with the given id.
	 * @param id identity of this composite, such as the bay number
	 */
    public MachineComposite(int id) {
        this(id, null);
    }
    
    /**
     * @param machine the machine component to add to this composite
     */
    public void add(MachineComponent machine) {
        machines.add(machine);
    }
	
	@Override
	public int getMachineCount() {
		int count = 0;
		for (MachineComponent machine : machines) {
			count += machine.getMachineCount();
		}
		return count;
	}

	@Override
	public Set<Object> getMaterial() {
		Set<Object> materialForAll = new HashSet<Object>();
		for (MachineComponent machine : machines) {
			materialForAll.addAll(machine.getMaterial());
		}
		return materialForAll;
	}

	@Override
	public Set<Object> getOwners() {
		Set<Object> ownersForAll = new HashSet<Object>();
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
	public boolean isTree(Set<MachineComponent> visited) {
		visited.add(this);
		for (MachineComponent machine : machines) {
			if (visited.contains(machine) || !machine.isTree(visited)) {
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
