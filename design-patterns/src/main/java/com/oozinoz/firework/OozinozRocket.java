package com.oozinoz.firework;

import com.oozinoz.simulation.RocketSim;

public class OozinozRocket extends PhysicalRocket implements RocketSim {

	private double time;
	
	public OozinozRocket(double burnArea, double burnRate, double fuelMass, double totalMass) {
		super(burnArea, burnRate, fuelMass, totalMass);
	}

	public double getMass() {
		return super.getMass(time);
	}

	public double getThrust() {
		return super.getThrust(time);
	}

	public void setSimTime(double time) {
		this.time = time;
	}
}
