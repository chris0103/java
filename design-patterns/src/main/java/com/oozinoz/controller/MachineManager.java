package com.oozinoz.controller;

public abstract class MachineManager {

	public abstract void startMachine();
	
	public abstract void stopMachine();
	
	public abstract void startProcess();
	
	public abstract void stopProcess();
	
	public abstract void conveyIn();
	
	public abstract void conveyOut();
	
	public abstract void shutdown();
}
