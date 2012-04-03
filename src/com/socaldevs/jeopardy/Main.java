package com.socaldevs.jeopardy;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SQLiteStorage storage = new SQLiteStorage("test.db");
		
		//storage.put("asdf", "fdsa", 0, 0, 99999);
		
		System.out.println(storage.getQuestion(0, 0));
		System.out.println(storage.getAnswer(0, 0));
		System.out.println(storage.getValue(0, 0));
	}

}
