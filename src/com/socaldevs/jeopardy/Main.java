package com.socaldevs.jeopardy;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SQLiteStorage.init("servite.db");
		
		GameEngine engine = GameEngine.getInstance();
		engine.showPlayers();
		engine.showQuestions();
		System.out.println("Created GameEngine");
	}

}
