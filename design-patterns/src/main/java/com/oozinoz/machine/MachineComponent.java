package com.oozinoz.machine;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class MachineComponent {

    protected int id;
    protected MachineComponent parent;
    protected Engineer responsible;
	
    /**
     * Create a machine component with the given id and parent
     * @param id this machine's identity
     * @param parent the machine composite that this component belongs to
     */
    public MachineComponent(int id, MachineComponent parent, Engineer responsible) {
        this.id = id;
        this.parent = parent;
        this.responsible = responsible;
    }
    
    /**
     * Create a machine component with the given id and parent
     * @param id this machine's identity
     * @param parent the machine composite that this component belongs to
     */
    public MachineComponent(int id, MachineComponent parent) {
        this(id, parent, null);
    }
    
    /**
     * Create a machine component with the given id.
     * @param id this machine's identity
     */
    public MachineComponent(int id) {
        this(id, null);
    }
    
    public boolean isTree() {
    	return isTree(new HashSet<MachineComponent>());
    }
	
	public abstract int getMachineCount();
	
	public abstract Set<Object> getMaterial();
	
	public abstract Set<Object> getOwners();
	
	public abstract boolean isCompletelyUp();
	
	public abstract boolean isTree(Set<MachineComponent> visited);
	
	public abstract boolean stopAll();
}
