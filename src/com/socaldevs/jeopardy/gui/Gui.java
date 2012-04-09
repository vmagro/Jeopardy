package com.socaldevs.jeopardy.gui;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class Gui extends JFrame{
	
	private static Gui instance;
	
	public static Gui getInstance(){
		if(instance == null)
			instance = new Gui();
		return instance;
	}
	
	private Gui(){
		setLayout(new GridLayout(5,6, 5, 5));
	}

}
