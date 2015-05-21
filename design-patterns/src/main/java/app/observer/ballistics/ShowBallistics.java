package app.observer.ballistics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.oozinoz.ballistics.Ballistics;
import com.oozinoz.ui.SwingFacade;

public class ShowBallistics {

	private double sliderMax;
	private double sliderMin;
	private JSlider slider;
	private BallisticsPanel burnPanel;
	private BallisticsPanel thrustPanel;
	private JLabel valueLabel;

	private Tpeak tPeak = new Tpeak(0);
	
	public JPanel mainPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(curvePanel(), BorderLayout.CENTER);
		panel.add(sliderBox(), BorderLayout.SOUTH);
		return panel;
	}
	
	private JPanel curvePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(SwingFacade.createTitledPanel("Burn Rate", burnPanel()));
		panel.add(SwingFacade.createTitledPanel("Thrust", thrustPanel()));
		return panel;
	}
	
	private BallisticsPanel burnPanel() {
		if (burnPanel == null) {
			burnPanel = new BallisticsPanel(Ballistics.rate(), tPeak);
			burnPanel.setPreferredSize(new Dimension(300, 200));
		}
		return burnPanel;
	}
	
	public BallisticsPanel thrustPanel() {
		if (thrustPanel == null) {
			thrustPanel = new BallisticsPanel(Ballistics.thrust(), tPeak);
			thrustPanel.setPreferredSize(new Dimension(300, 200));
		}
		return thrustPanel;
	}
	
	private Box sliderBox() {
		Box box = Box.createHorizontalBox();
		JLabel label = new JLabel("tPeak");
		label.setFont(SwingFacade.getStandardFont());
		label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		label.setForeground(Color.black);
		box.add(label);
		box.add(valueLabel());
		box.add(slider());
		return box;
	}
	
	private JLabel valueLabel() {
		if (valueLabel == null) {
			valueLabel = new BallisticsLabel(tPeak);
			valueLabel.setFont(SwingFacade.getStandardFont());
			valueLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
			valueLabel.setForeground(Color.black);
		}
		return valueLabel;
	}
	
	public JSlider slider() {
		if (slider == null) {
			slider = new JSlider();
			sliderMax = slider.getMaximum();
			sliderMin = slider.getMinimum();
			slider.addChangeListener(new ChangeListener() {

				public void stateChanged(ChangeEvent e) {
					if (sliderMax == sliderMin) {
						return;
					}
					tPeak.setValue((slider.getValue() - sliderMin) / (sliderMax - sliderMin));
				}
			});
			slider.setValue(slider.getMinimum());
		}
		return slider;
	}
	
    public static void main(String[] args) {
        SwingFacade.launch(new ShowBallistics().mainPanel(), "Effects of tPeak");
    }
}
