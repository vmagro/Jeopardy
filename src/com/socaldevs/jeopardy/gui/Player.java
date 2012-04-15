package com.socaldevs.jeopardy.gui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name = "";
	private int score = 0;

	private JLabel scoreLabel = new JLabel();
	
	public String getName(){
		return name ;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setScore(int score){
		this.score = score;
		scoreLabel.setText("$"+String.valueOf(score));
	}
	
	public void addScore(int add){
		setScore(getScore()+add);
	}

	public int getScore() {
		return score;
	}
	
	public Player(String name){
		setName(name);
		
		setLayout(new GridLayout(0, 1));
		
		JLabel nameLabel = new JLabel(name);
		nameLabel.setFont(Font.getFont("Bradley Hand ITC"));
		add(nameLabel);
		
		add(scoreLabel);
	}
}
