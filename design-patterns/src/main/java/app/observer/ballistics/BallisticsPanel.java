package app.observer.ballistics;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.oozinoz.ballistics.BallisticsFunction;

public class BallisticsPanel extends JPanel implements ChangeListener {

	private double tPeak;
	private BallisticsFunction func;
	private JSlider slider;
	
	public BallisticsPanel(BallisticsFunction func, JSlider slider) {
		this.func = func;
		this.slider = slider;
		slider.addChangeListener(this);
	}
	
	public void setTPeak(double tPeak) {
		this.tPeak = tPeak;
	}

	public void stateChanged(ChangeEvent e) {
		
	}
}
