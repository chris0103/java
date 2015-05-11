package app.facade;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.oozinoz.ui.SwingFacade;

public class ShowCircle extends JPanel {

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int pointCount = 101;
		double width = getWidth() -1 ;
		double height = getHeight() - 1;
		double r = Math.min(width, height) / 2.0;
		int[] x = new int[pointCount];
		int[] y = new int[pointCount];
		for (int i = 0; i < pointCount; i++) {
			double t = ((double) i) / (pointCount - 1);
			double theta = Math.PI * 2.0 * t;
			x[i] = (int) (width / 2 + r * Math.cos(theta));
			y[i] = (int) (height / 2 - r * Math.sin(theta));
		}
		g.drawPolyline(x, y, pointCount);
	}
	
	public static void main(String[] args) {
		ShowCircle sc = new ShowCircle();
		sc.setPreferredSize(new Dimension(300, 200));
		SwingFacade.launch(sc, "Circle");
	}
}
