package app.observer.ballistics;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import com.oozinoz.ballistics.BallisticsFunction;

public class BallisticsPanel extends JPanel implements Observer {

	private int nPoints = 101;
	private int[] xPoints = new int[nPoints];
	private int[] yPoints = new int[nPoints];
	
	private double tPeak;
	private BallisticsFunction func;
	
	
	public BallisticsPanel(BallisticsFunction func, Tpeak tPeak) {
		this.func = func;
		tPeak.addObserver(this);
	}

	public void update(Observable obj, Object arg) {
		tPeak = ((Tpeak) obj).getValue();
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < nPoints; i++) {
			double t = ((double) i) / (nPoints - 1);
			xPoints[i] = (int) (t * getWidth());
			yPoints[i] = (int) (getHeight() * (1 - func.function(t, tPeak)));
		}
		g.drawPolyline(xPoints, yPoints, nPoints);
	}
}
