package com.oozinoz.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Rocket extends Remote {

	public void boost(double factor) throws RemoteException;
	
	public double getApogee() throws RemoteException;
	
	public double getPrice() throws RemoteException;
}
