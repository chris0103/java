package com.oozinoz.firework;

public class PhysicalRocket {

	private double burnArea;
	private double burnRate;
	private double fuelMass;
	private double totalMass;
	
	public PhysicalRocket(double burnArea, double burnRate, double fuelMass, double totalMass) {
		this.burnArea = burnArea;
		this.burnRate = burnRate;
		this.fuelMass = fuelMass;
		this.totalMass = totalMass;
	}

	public double getBurnTime() {
		return 0;
	}
	
	public double getMass(double t) {
		return 0;
	}
	
	public double getThrust(double t) {
		return 0;
	}
}
