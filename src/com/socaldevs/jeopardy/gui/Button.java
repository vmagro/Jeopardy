package com.socaldevs.jeopardy.gui;

import java.awt.Color;

import javax.swing.JButton;

import com.socaldevs.jeopardy.GameEngine;

public class Button extends JButton implements ButtonInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int questionId = 0;
	
	public Button(int col, int row){
		setText(String.valueOf(GameEngine.getInstance().getStorage().getQuestion(col, row).getValue()));
		setBackground(Color.BLUE);
		questionId = GameEngine.getInstance().getStorage().getId(col, row);
	}
	
	public int getQuestionId(){
		return questionId;
	}
	
	public void deactivate(){
		//setBackground(Color.GRAY);
		setEnabled(false);
	}

}
