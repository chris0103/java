package app.facade;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class ShowFlight extends JPanel {

	public static Font getStandardFont() {
		return new Font("Dialog", Font.PLAIN, 18);
	}
	
	public static TitledBorder createTitledBorder(String title) {
		TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), title, TitledBorder.LEFT, TitledBorder.TOP);
		tb.setTitleColor(Color.black);
		tb.setTitleFont(getStandardFont());
		return tb;
	}
	
	public static JPanel createTitledPanel(String title, JPanel in) {
		JPanel out = new JPanel();
		out.add(in);
		out.setBorder(createTitledBorder(title));
		return out;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int pointCount = 101;
		double width = getWidth() - 1;
		double height = getHeight() - 1;
		int[] x = new int[pointCount];
		int[] y = new int[pointCount];
		for (int i = 0; i < pointCount; i++) {
			double t = ((double) i) / (pointCount - 1);
			x[i] = (int) (t * width);
			y[i] = (int) (4 * height * (t - 0.5) * (t - 0.5));
		}
		g.drawPolyline(x, y, pointCount);
	}
	
	public static void main(String[] args) {
		ShowFlight flight = new ShowFlight();
		flight.setPreferredSize(new Dimension(300, 200));
		JPanel panel = createTitledPanel("Flight Path", flight);
		JFrame frame = new JFrame("Flight Path for Shell Duds");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
