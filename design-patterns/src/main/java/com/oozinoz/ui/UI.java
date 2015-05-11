package com.oozinoz.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class UI {
	
	public static final UI NORMAL = new UI();
	
	protected Font font = new Font("Book Antiqua", Font.PLAIN, 18);
	
	public TitledBorder createTitledBorder(String title) {
		TitledBorder border = BorderFactory.createTitledBorder(
				BorderFactory.createBevelBorder(BevelBorder.RAISED), title, TitledBorder.LEFT, TitledBorder.TOP);
		border.setTitleColor(Color.black);
		border.setTitleFont(getFont());
		return border;
	}
	
	public JPanel createTitledPanel(String title, JPanel in) {
		JPanel out = new JPanel();
		out.add(in);
		out.setBorder(createTitledBorder(title));
		return out;
	}
	
	public Font getFont() {
		return font;
	}
}
