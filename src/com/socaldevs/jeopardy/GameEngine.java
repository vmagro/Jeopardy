package com.socaldevs.jeopardy;

import com.socaldevs.jeopardy.gui.Player;
import com.socaldevs.jeopardy.gui.PlayerFrame;
import com.socaldevs.jeopardy.gui.QuestionFrame;
import com.socaldevs.jeopardy.gui.QuestionsFrame;

public class GameEngine {

	private static GameEngine instance = null;
	
	public static GameEngine getInstance(){
		if(instance == null)
			instance = new GameEngine();
		return instance;
	}
	
	private Player player = null;
	private SQLiteStorage storage = null;
	private PlayerFrame playerFrame = null;
	private QuestionsFrame questionsFrame = null;
	
	private GameEngine(){
		storage = SQLiteStorage.getInstance();
		player = new Player("You");
	}
	
	public SQLiteStorage getStorage(){
		return storage;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public void registerIncorrect(int value){
		player.addScore(-value);
	}
	
	public void registerCorrect(int value){
		player.addScore(value);
	}
	
	public void showPlayers(){
		if(playerFrame == null)
			playerFrame = new PlayerFrame();
	}

	public void showQuestions() {
		if(questionsFrame == null)
			questionsFrame = QuestionsFrame.getInstance();
	}

	public void showQuestion(int questionId) {
		QuestionFrame frame = new QuestionFrame(questionId);
		frame.setLocation(questionsFrame.getX()+(questionsFrame.getWidth()/2) - (frame.getWidth()/2), questionsFrame.getY()+(questionsFrame.getHeight()));
		frame.setResizable(false);
	}
}
