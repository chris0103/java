package com.oozinoz.controller;

public class StarPressManager extends MachineManager {

	@Override
	public void startMachine() {
	}

	@Override
	public void stopMachine() {
	}

	@Override
	public void startProcess() {
	}

	@Override
	public void stopProcess() {
	}

	@Override
	public void conveyIn() {
	}

	@Override
	public void conveyOut() {
	}

	@Override
	public void shutdown() {
		stopProcess();
		conveyOut();
		stopMachine();
	}
}
