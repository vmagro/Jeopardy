package com.socaldevs.jeopardy.gui;

import java.awt.Color;

import javax.swing.JButton;

import com.socaldevs.jeopardy.SQLiteStorage;

public class Button extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int questionId;
	
	public Button(int questionId){
		setText(String.valueOf(SQLiteStorage.getInstance().getQuestion(questionId).getValue()));
		setBackground(Color.BLUE);
		this.questionId = questionId;
	}
	
	public int getQuestionId(){
		return questionId;
	}

}
