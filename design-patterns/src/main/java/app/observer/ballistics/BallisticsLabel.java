package app.observer.ballistics;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BallisticsLabel extends JLabel implements ChangeListener {

	private double tPeak;
	private JSlider slider;
	
	public BallisticsLabel(JSlider slider) {
		this.slider = slider;
		slider.addChangeListener(this);
	}
	
	public void stateChanged(ChangeEvent e) {
		double val = slider.getValue();
		double max = slider.getMaximum();
		double min = slider.getMinimum();
		tPeak = (val - min) / (max - min);
		repaint();
	}
}
