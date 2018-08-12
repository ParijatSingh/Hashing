package main;

import javax.swing.JFrame;

import ui.MainFrame;

public class Runner {

	public static void main(String[] args) throws Exception{
		
		Dictionary.initialize("dictionary.txt.zip");
		
		MainFrame mainFrame = new MainFrame();
		mainFrame.setSize(800, 400);
		mainFrame.setVisible(true);
		
		
	}

}
