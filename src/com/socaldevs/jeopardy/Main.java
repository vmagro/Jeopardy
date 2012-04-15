package com.socaldevs.jeopardy;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SQLiteStorage.init("servite.db");
		//SQLiteStorage storage = SQLiteStorage.getInstance();		
		
		//QuestionFrame frame = new QuestionFrame(1);
		
		GameEngine engine = GameEngine.getInstance();
		engine.showPlayers();
		engine.showQuestions();
		System.out.println("Created GameEngine");
	}

}
