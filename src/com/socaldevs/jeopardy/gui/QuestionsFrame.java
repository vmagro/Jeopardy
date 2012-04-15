package com.socaldevs.jeopardy.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.socaldevs.jeopardy.GameEngine;

public class QuestionsFrame extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static QuestionsFrame instance;
	
	public static QuestionsFrame getInstance(){
		if(instance == null)
			instance = new QuestionsFrame();
		return instance;
	}
	
	
	private QuestionsFrame(){
		setTitle("Questions");
		setLayout(new GridLayout(4, 4, 5, 5));
		for(String cat : GameEngine.getInstance().getStorage().getCategories())
			add(new JLabel(cat));
		for(int row=0; row<3; row++)
			for(int col=0; col<4; col++){
				Button b = new Button(row, col);
				b.addActionListener(this);
				add(b);
			}
		
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Button b = (Button) e.getSource();
		GameEngine.getInstance().showQuestion(b.getQuestionId());
	}

}
