package com.oozinoz.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.oozinoz.function.Function;

public class PlotPanel extends JPanel {

	private int points;
	private int[] xPoints;
	private int[] yPoints;
	private Function xFunction;
	private Function yFunction;
	
	public PlotPanel(int nPoint, Function xFunc, Function yFunc) {
		this.points = nPoint;
		this.xPoints = new int[points];
		this.yPoints = new int[points];
		this.xFunction = xFunc;
		this.yFunction = yFunc;
		setBackground(Color.WHITE);
	}
	
	protected void paintComponent(Graphics g) {
		double width = getWidth() - 1;
		double height = getHeight() - 1;
		for (int i = 0; i < points; i++) {
			double t = ((double) i) / (points - 1);
			xPoints[i] = (int) (xFunction.f(t) * width);
			yPoints[i] = (int) (height * (1 - yFunction.f(t)));
		}
		g.drawPolyline(xPoints, yPoints, points);
	}
}
