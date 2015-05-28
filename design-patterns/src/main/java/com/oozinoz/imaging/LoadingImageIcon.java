package com.oozinoz.imaging;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class LoadingImageIcon extends ImageIcon implements Runnable {

	private static final ImageIcon ABSENT = new ImageIcon(ClassLoader.getSystemResource("images/absent.jpg"));
	private static final ImageIcon LOADING = new ImageIcon(ClassLoader.getSystemResource("images/loading.jpg"));
	
	private String filename;
	private JFrame callbackFrame;
	
	public LoadingImageIcon(String filename) {
		super(ABSENT.getImage());
		this.filename = filename;
	}
	
	public void load() {
		setImage(LOADING.getImage());
		callbackFrame.repaint();
		new Thread(this).start();
	}
	
	public void run() {
		setImage(new ImageIcon(ClassLoader.getSystemResource(filename)).getImage());
		callbackFrame.repaint();
		callbackFrame.pack();
	}
}
