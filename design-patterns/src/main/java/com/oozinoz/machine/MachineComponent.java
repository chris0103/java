package com.oozinoz.machine;

import java.util.List;

public abstract class MachineComponent {

	public abstract int getMachineCount();
	
	public abstract List<Object> getMaterial();
	
	public abstract List<Object> getOwners();
	
	public abstract boolean isCompletelyUp();
	
	public abstract boolean stopAll();
}
