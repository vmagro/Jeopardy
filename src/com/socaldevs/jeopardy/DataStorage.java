package com.socaldevs.jeopardy;

import java.util.List;

public abstract class DataStorage {

	public abstract Question getQuestion(int col, int row);
	public abstract Question getQuestion(int id);
	
	public abstract void put(String question, String answer, int col, int row, int value);
	public abstract void put(Question question);
	
	public abstract void addCategory(String cat, int num);
	public abstract List<String> getCategories();
	
}
