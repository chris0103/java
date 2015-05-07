package com.oozinoz.simulation;

public interface RocketSim {

	abstract double getMass();
	
	public double getThrust();
	
	void setSimTime(double time);
}
