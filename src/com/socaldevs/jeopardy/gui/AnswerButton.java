package com.socaldevs.jeopardy.gui;

import java.awt.Color;

import javax.swing.JButton;

import com.socaldevs.jeopardy.GameEngine;

public class AnswerButton extends JButton implements ButtonInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int questionId = 0;
	
	public AnswerButton(int id){
		this.questionId = id;
		setText(GameEngine.getInstance().getStorage().getAnswer(id));
		setBackground(Color.BLUE);
	}
	
	public int getQuestionId(){
		return questionId;
	}

}
