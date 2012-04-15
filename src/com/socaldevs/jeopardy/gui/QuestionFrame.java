package com.socaldevs.jeopardy.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.socaldevs.jeopardy.GameEngine;
import com.socaldevs.jeopardy.SQLiteStorage;

public class QuestionFrame extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int questionId;

	public QuestionFrame(int id){
		this.questionId = id;
		
		setTitle("Question");
		
		setLayout(new GridLayout(5, 1));
		
		SQLiteStorage storage = GameEngine.getInstance().getStorage();
		JLabel questionLabel = new JLabel(storage.getQuestion(id).getQuestion());
		add(questionLabel);
		
		Random random = new Random();
		ArrayList<Integer> ints = new ArrayList<Integer>();
		ints.add(id);
		int max = storage.numQuestions()-1;
		for(int i=0; i<3; i++){ //pick 3 random question indices
			int randint = random.nextInt(max)+1;
			if(ints.contains(randint)) //already picked, don't choose again
				i--;
			else
				ints.add(randint);
		}
		Collections.shuffle(ints); //rearrange buttons so that answer is not always first
		
		AnswerButton[] buttons = new AnswerButton[4];
		for(int i=0; i<buttons.length; i++){
			buttons[i] = new AnswerButton(ints.get(i));
		}
		for(AnswerButton b : buttons){
			add(b);
			b.addActionListener(this);
		}
		
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() instanceof AnswerButton){
			AnswerButton b = (AnswerButton) ae.getSource();
			int qid = b.getQuestionId();
			
			JDialog dialog = new JDialog();
			JLabel label = new JLabel();
			if(qid == this.questionId){
				label.setText("Correct!");
				GameEngine.getInstance().registerCorrect(GameEngine.getInstance().getStorage().getQuestion(questionId).getValue());
			}
			else{
				label.setText("Sorry, but that is incorrect");
				GameEngine.getInstance().registerIncorrect(GameEngine.getInstance().getStorage().getQuestion(questionId).getValue());
			}
			dialog.setLocation(getX(), getY());
			this.setVisible(false);
			dialog.setContentPane(label);
			dialog.pack();
			dialog.setVisible(true);
			
		}
	}
	
}
