package com.socaldevs.jeopardy.gui;

import java.awt.GridLayout;

import javax.swing.JFrame;

import com.socaldevs.jeopardy.GameEngine;

public class PlayerFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public PlayerFrame(){
		setTitle("Player");
		
		//setLayout(new GridLayout(1,3));
		
		//add(new Player("Bob"));
		add(GameEngine.getInstance().getPlayer());
		//add(new Player("Watson"));
		
		pack();
		setVisible(true);
	}

}
