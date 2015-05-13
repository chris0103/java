package com.oozinoz.machine;

public class OozinozFactory {

	public static MachineComponent plant() {
		MachineComposite plant = new MachineComposite(100);
		MachineComposite bay = new MachineComposite(101);
		Machine mixer = new Mixer(102);
		Machine press = new StarPress(103);
		Machine assembler = new ShellAssembler(104);
		bay.add(mixer);
		bay.add(press);
		bay.add(assembler);
		plant.add(mixer);
		plant.add(bay);
		return plant;
	}
}
