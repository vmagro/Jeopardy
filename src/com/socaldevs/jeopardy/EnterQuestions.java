package com.socaldevs.jeopardy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EnterQuestions {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		SQLiteStorage.init("test.db");
		SQLiteStorage storage = SQLiteStorage.getInstance();
		
		int row = 0;
		int col = 0;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<5; i++){
			String input = in.readLine();
		}
		
	}

}
