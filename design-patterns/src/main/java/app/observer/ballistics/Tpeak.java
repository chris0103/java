package app.observer.ballistics;

import java.util.Observable;

public class Tpeak extends Observable {

	protected double value;
	
	public Tpeak(double value) {
		this.value = value;
	}
	
	public void setValue(double value) {
		this.value = value;
		setChanged();
		notifyObservers();
	}
	
	public double getValue() {
		return value;
	}
}
