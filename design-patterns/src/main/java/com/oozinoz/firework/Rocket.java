package com.oozinoz.firework;

import com.oozinoz.utility.Dollars;

public class Rocket {

	private String name;
	private double mass;
	private Dollars price;
	private double apogee;
	private double thrust;
	
	public Rocket(String name, double mass, Dollars price, double apogee, double thrust) {
		this.name = name;
		this.mass = mass;
		this.price = price;
		this.apogee = apogee;
		this.thrust = thrust;
	}

	public String getName() {
		return name;
	}

	public double getMass() {
		return mass;
	}

	public Dollars getPrice() {
		return price;
	}

	public double getApogee() {
		return apogee;
	}

	public double getThrust() {
		return thrust;
	}
}
