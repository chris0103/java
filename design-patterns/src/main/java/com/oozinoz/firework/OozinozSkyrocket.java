package com.oozinoz.firework;

import com.oozinoz.simulation.Skyrocket;

public class OozinozSkyrocket extends Skyrocket {
	
	private PhysicalRocket rocket;
	
	public OozinozSkyrocket(PhysicalRocket rocket) {
		super(rocket.getMass(0), rocket.getThrust(0), rocket.getBurnTime());
		this.rocket = rocket;
	}
	
	public double getMass() {
		return rocket.getMass(simTime);
	}
	
	public double getThrust() {
		return rocket.getThrust(simTime);
	}
}
