package com.socaldevs.jeopardy;

public interface DataStorage {

	public Question getQuestion(int col, int row);
	public Question getQuestion(int id);
	public void put(String question, String answer, int row, int col, int value);
	
}
