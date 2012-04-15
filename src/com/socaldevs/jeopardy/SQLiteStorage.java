package com.socaldevs.jeopardy;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SQLiteStorage extends DataStorage{

	private static final String CAT_TABLE = "categories";
	private static final String CAT_NAME = "name";
	private static final String CAT_INDEX = "num";
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
	private static final String CREATE_CAT_TABLE = "CREATE TABLE IF NOT EXISTS "+CAT_TABLE+" (" +
			CAT_NAME+" TEXT," +
			CAT_INDEX+" INTEGER" +
			");";

	private static final String SELECT_BY_POS = "SELECT %s FROM "+TABLE+" WHERE "+COLUMN+"=%d AND "+ROW+"=%d";
	private static final String SELECT_BY_ID = "SELECT * FROM "+TABLE+" WHERE "+ID+"=%d";

	private static final String INSERT_QUESTION = "INSERT INTO "+TABLE+
			" VALUES(NULL, '%s','%s',%d,%d,%d);";
	
	private Connection conn = null;

	
	private static SQLiteStorage instance = null;
	
	public static SQLiteStorage getInstance(){
		if(instance == null)
			throw new StorageNotInitializedException();
		return instance;
	}
	
	public static void init(String name){
		String url = ClassLoader.getSystemResource(name).toExternalForm();
		if(url.contains("file:"))
			url = url.replace("file:", "");
		else if(url.contains("jar:"))
			url = url.replace("jar:", ":resource:");
		instance = new SQLiteStorage(url);
	}
	
	private SQLiteStorage(String database){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//load sqlite driver

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:"+database,"","");
			Statement createStatement = conn.createStatement();
			createStatement.execute(CREATE_TABLE);
			createStatement.execute(CREATE_CAT_TABLE);
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
		q.setId(getId(col, row));
		return q;
	}
	
	@Override
	public Question getQuestion(int id){
		Question q = new Question();
		try{
			Statement query = conn.createStatement();
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
	
	public String getAnswer(int id){
		return getQuestion(id).getAnswer();
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
	
	public int getId(int col, int row){
		try{
			Statement query = conn.createStatement();
			ResultSet result = query.executeQuery(String.format(SELECT_BY_POS, ID, col, row));
			return result.getInt(ID);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void put(String question, String answer, int col, int row, int value) {
		question = question.replace("'", "''");
		answer = answer.replace("'", "''");
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
	
	public int numQuestions(){
		try{
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT MAX("+ID+") FROM "+TABLE);
			return result.getInt(1);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void addCategory(String cat, int num) {
		cat = cat.replace("'", "''");
		try{
			Statement stmt = conn.createStatement();
			stmt.execute("INSERT INTO "+CAT_TABLE+" VALUES('"+cat+"',"+num+");");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getCategories() {
		ArrayList<String> cats = new ArrayList<String>();
		try{
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM "+CAT_TABLE);
			while(result.next()){
				cats.add(result.getString(CAT_NAME));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return cats;
	}
	
	public void resetDb(){
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("DROP TABLE "+TABLE);
			stmt.execute("DROP TABLE "+CAT_TABLE);
			stmt.execute(CREATE_CAT_TABLE);
			stmt.execute(CREATE_TABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static class StorageNotInitializedException extends RuntimeException{

		public StorageNotInitializedException(){
			super("Storage not initialized. Call init() first");
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	}
}
