package com.oozinoz.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RocketImpl extends UnicastRemoteObject implements Rocket {

	private double price;
	private double apogee;
	
	public RocketImpl(double price, double apogee) throws RemoteException {
		this.price = price;
		this.apogee = apogee;
	}
	
	public void boost(double factor) throws RemoteException {
		apogee *= factor;
	}

	public double getApogee() throws RemoteException {
		return apogee;
	}

	public double getPrice() throws RemoteException {
		return price;
	}
}
