package com.socaldevs.jeopardy;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteStorage implements DataStorage{

	private static final String TABLE = "jeopardy";
	private static final String QUESTION = "question";
	private static final String ANSWER = "answer";
	private static final String ROW = "row";
	private static final String COLUMN = "column";
	private static final String VALUE = "value";
	private static final String ID = "id";

	private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
			ID+" INTEGER PRIMARY KEY," +
			QUESTION+" TEXT," +
			ANSWER+" TEXT," +
			COLUMN+" INTEGER," +
			ROW+" INTEGER," +
			VALUE+" INTEGER" +
			");";

	private static final String SELECT_BY_POS = "SELECT %s FROM "+TABLE+" WHERE "+COLUMN+"=%d AND "+ROW+"=%d";
	private static final String SELECT_BY_ID = "SELECT * FROM "+TABLE+" WHERE "+ID+"=%d";

	private static final String INSERT_QUESTION = "INSERT INTO "+TABLE+
			" VALUES(NULL, '%s','%s',%d,%d,%d);";
	
	private Connection conn = null;

	
	private static SQLiteStorage instance = null;
	
	public static SQLiteStorage getInstance(){
		return instance;
	}
	
	public static void init(File f){
		instance = new SQLiteStorage(f);
	}
	
	public static void init(String path){
		init(new File(path));
	}
	
	private SQLiteStorage(File database){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//load sqlite driver

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:"+database.getAbsolutePath(),"","");
			Statement createStatement = conn.createStatement();
			createStatement.execute(CREATE_TABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		instance = this;
	}
	
	@Override
	public Question getQuestion(int col, int row){
		Question q = new Question();
		q.setCol(col);
		q.setRow(row);
		q.setAnswer(getAnswer(col, row));
		q.setQuestion(getQuestionString(col, row));
		q.setValue(getValue(col, row));
		return q;
	}
	
	@Override
	public Question getQuestion(int id){
		Question q = new Question();
		try{
			Statement query = conn.createStatement();
			System.out.println(String.format(SELECT_BY_ID, id));
			ResultSet result = query.executeQuery(String.format(SELECT_BY_ID, id));
			q.setCol(result.getInt(COLUMN));
			q.setRow(result.getInt(ROW));
			q.setQuestion(result.getString(QUESTION));
			q.setAnswer(result.getString(ANSWER));
			q.setValue(result.getInt(VALUE));
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return q;
	}

	public String getQuestionString(int col, int row) {
		try{
			Statement query = conn.createStatement();
			ResultSet result = query.executeQuery(String.format(SELECT_BY_POS, QUESTION, col, row));
			return result.getString(QUESTION);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return "";
	}

	public String getAnswer(int col, int row) {
		try{
			Statement query = conn.createStatement();
			ResultSet result = query.executeQuery(String.format(SELECT_BY_POS, ANSWER, col, row));
			return result.getString(ANSWER);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return "";
	}

	public int getValue(int col, int row) {
		try{
			Statement query = conn.createStatement();
			ResultSet result = query.executeQuery(String.format(SELECT_BY_POS, VALUE, col, row));
			return result.getInt(VALUE);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void put(String question, String answer, int col, int row, int value) {
		try {
			Statement statement = conn.createStatement();
			statement.execute(String.format(INSERT_QUESTION, question, answer, row, col, value));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void put(Question question) {
		put(question.getQuestion(), question.getAnswer(), question.getCol(), question.getRow(), question.getValue());
	}


}
