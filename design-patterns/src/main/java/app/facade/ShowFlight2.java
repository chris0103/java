package app.facade;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.oozinoz.function.Function;
import com.oozinoz.function.T;
import com.oozinoz.ui.PlotPanel;
import com.oozinoz.ui.UI;

public class ShowFlight2 {

	public static void main(String[] args) {
		PlotPanel plot = new PlotPanel(101, new T(), new ShowFlight2().new YFunction());
		plot.setPreferredSize(new Dimension(300, 200));
		JFrame frame = new JFrame("Flight Path for Shell Duds");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(UI.NORMAL.createTitledPanel("Flight Path", plot));
		frame.pack();
		frame.setVisible(true);
	}
	
	private class YFunction extends Function {

		public double f(double t) {
			return 4 * t * (1 - t);
		}
	}
}
