package com.oozinoz.simulation;

public class Skyrocket {

	protected double simTime;
	
	private double mass;
	private double thrust;
	private double burnTime;
	
	public Skyrocket(double mass, double thrust, double burnTime) {
		this.mass = mass;
		this.thrust = thrust;
		this.burnTime = burnTime;
	}

	public double getMass() {
		return mass;
	}

	public double getThrust() {
		return thrust;
	}

	public double getBurnTime() {
		return burnTime;
	}
}
