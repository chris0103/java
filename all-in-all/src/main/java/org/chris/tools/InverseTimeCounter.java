package org.chris.tools;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.Calendar;

import javax.swing.*;
import static javax.swing.SwingConstants.*;

public class InverseTimeCounter extends JFrame {

	private static final int MINUTE_OF_HOUR = 60;
	private static final int SECOND_OF_MINUTE = 60;
	private static final int HOUR_OF_DAY = 24;

	private long leftSeconds;
	private long totalSeconds = SECOND_OF_MINUTE * MINUTE_OF_HOUR * HOUR_OF_DAY	* 4;

	private static JLabel timeTab;
	
	private static JPanel timePanel;
	
	private static Color timePanelColor;

	public InverseTimeCounter() {

		Calendar endTime = new GregorianCalendar(2021, Calendar.FEBRUARY, 1);
		Calendar now = Calendar.getInstance();
		leftSeconds = (endTime.getTime().getTime() - now.getTime().getTime())
				/ 1000 - 6 * MINUTE_OF_HOUR * SECOND_OF_MINUTE;
		timeTab = new JLabel();
		timeTab.setBorder(BorderFactory.createEtchedBorder());
		Font font = new Font("SansSerif", Font.PLAIN, 12);
		timeTab.setFont(font);
		timeTab.setHorizontalAlignment(CENTER);
		timeTab.setForeground(new Color(255,0,0));
		timeTab.setText(String.valueOf(leftSeconds));

		JButton slowDown = new JButton("<");
		JButton speedUp = new JButton(">");
		font.deriveFont(2f);
		slowDown.setFont(font);
		speedUp.setFont(font);
		slowDown.setHorizontalAlignment(RIGHT);
		speedUp.setHorizontalAlignment(LEFT);
		slowDown.setPreferredSize(new Dimension(45, 20));
		speedUp.setPreferredSize(new Dimension(45, 20));
		slowDown.addActionListener(new ButtonHandler(1));
		speedUp.addActionListener(new ButtonHandler(2));

		timePanel = new JPanel();

		timePanel.add(slowDown);
		timePanel.add(timeTab);
		timePanel.add(speedUp);

		getContentPane().add(timePanel, BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		pack();
		setLocation(500, 600);
		setVisible(true);

	}

	public void tick() {

		while (leftSeconds > 0) {
			--leftSeconds;
			timeTab.setText(String.valueOf(leftSeconds));
			Long longColor = (totalSeconds - leftSeconds) * 255 / totalSeconds;
			int color = longColor.intValue();
			timePanelColor = new Color(color,color,color);
			timePanel.setBackground(timePanelColor);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		timeTab.setText("Time is over! We can go to Fuzhou~~~~!");
	}

	class ButtonHandler implements ActionListener {

		public ButtonHandler(int mode) {
			this.mode = mode;
		}

		public void actionPerformed(ActionEvent e) {
			if (mode == 1) {
				++leftSeconds;
				timeTab.setText(String.valueOf(leftSeconds));
			} else if (mode == 2) {
				--leftSeconds;
				timeTab.setText(String.valueOf(leftSeconds));
			}

		}

		int mode = 0;
	}

	public static void main(String[] args) {
		InverseTimeCounter count = new InverseTimeCounter();
		count.tick();
	}
}
