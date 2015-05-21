package app.observer.ballistics;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

public class BallisticsLabel extends JLabel implements Observer {

	public BallisticsLabel(Tpeak tPeak) {
		tPeak.addObserver(this);
	}

	public void update(Observable obj, Object arg) {
		Tpeak tPeak = (Tpeak) obj;
		setText(String.valueOf(tPeak.getValue()));
		repaint();
	}
}
