package app.observer.ballistics;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ShowBallistics {

	private int sliderMax;
	private int sliderMin;
	private JSlider slider;
	private BallisticsPanel burnPanel;
	private BallisticsPanel thrustPanel;
	private JLabel valueLabel;
	
	public JSlider slider() {
		if (slider == null) {
			slider = new JSlider();
			sliderMax = slider.getMaximum();
			sliderMin = slider.getMinimum();
			slider.setValue(slider.getMinimum());
		}
		return slider;
	}
	
	public BallisticsPanel burnPanel() {
		if (burnPanel == null) {
			burnPanel = new BallisticsPanel();
		}
		return burnPanel;
	}
	
	public BallisticsPanel thrustPanel() {
		if (thrustPanel == null) {
			thrustPanel = new BallisticsPanel();
		}
		return thrustPanel;
	}
	
	public JLabel valueLabel() {
		if (valueLabel == null) {
			valueLabel = new JLabel();
		}
		return valueLabel;
	}

	public void stateChanged(ChangeEvent e) {
		double val = slider.getValue();
		double tp = (val - sliderMin) / (sliderMax - sliderMin);
		burnPanel().setTPeak(tp);
		thrustPanel.setTPeak(tp);
		valueLabel().setText(String.valueOf(tp));
	}
}
