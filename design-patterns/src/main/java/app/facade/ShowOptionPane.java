package app.facade;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ShowOptionPane {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				Font font = new Font("Dialog", Font.PLAIN, 18);
				UIManager.put("Button.font", font);
				UIManager.put("Label.font", font);
				int option = 0;
				do {
					option = JOptionPane.showConfirmDialog(null, "Had enough?", "A Stubborn Dialog", JOptionPane.YES_NO_OPTION);
				} while (option == JOptionPane.NO_OPTION);
			}
		});
	}
}
