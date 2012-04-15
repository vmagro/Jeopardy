package com.socaldevs.jeopardy.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.socaldevs.jeopardy.SQLiteStorage;

public class EditGui extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static SQLiteStorage stor = null;
	
	private JTextField cat1, cat2, cat3, cat4, cat5;
	private JTextField cat11, cat12, cat13;
	private JTextField cat21, cat22, cat23;
	private JTextField cat31, cat32, cat33;
	private JTextField cat41, cat42, cat43;
	private JTextField cat51, cat52, cat53;
	
	private JTextField ans11, ans12, ans13;
	private JTextField ans21, ans22, ans23;
	private JTextField ans31, ans32, ans33;
	private JTextField ans41, ans42, ans43;
	private JTextField ans51, ans52, ans53;
	
	private JButton submit1, submit2, submit3, submit4, submit5;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SQLiteStorage.init("test.db");
		stor = SQLiteStorage.getInstance();
		new EditGui();
	}
	
	public EditGui(){
		setTitle("Edit");
		setLayout(new GridLayout(8, 5));
		
		cat1 = new JTextField("CAT");
		cat2 = new JTextField("CAT");
		cat3 = new JTextField("CAT");
		cat4 = new JTextField("CAT");
		cat5 = new JTextField("CAT");
		
		cat11 = new JTextField("Q");
		cat12 = new JTextField("Q");
		cat13 = new JTextField("Q");
		cat21 = new JTextField("Q");
		cat22 = new JTextField("Q");
		cat23 = new JTextField("Q");
		cat31 = new JTextField("Q");
		cat32 = new JTextField("Q");
		cat33 = new JTextField("Q");
		cat41 = new JTextField("Q");
		cat42 = new JTextField("Q");
		cat43 = new JTextField("Q");
		cat51 = new JTextField("Q");
		cat52 = new JTextField("Q");
		cat53 = new JTextField("Q");
		
		ans11 = new JTextField("A");
		ans12 = new JTextField("A");
		ans13 = new JTextField("A");
		ans21 = new JTextField("A");
		ans22 = new JTextField("A");
		ans23 = new JTextField("A");
		ans31 = new JTextField("A");
		ans32 = new JTextField("A");
		ans33 = new JTextField("A");
		ans41 = new JTextField("A");
		ans42 = new JTextField("A");
		ans43 = new JTextField("A");
		ans51 = new JTextField("A");
		ans52 = new JTextField("A");
		ans53 = new JTextField("A");
		
		submit1 = new JButton("Submit");
		submit2 = new JButton("Submit");
		submit3 = new JButton("Submit");
		submit4 = new JButton("Submit");
		submit5 = new JButton("Submit");
		
		add(cat1);
		add(cat2);
		add(cat3);
		add(cat4);
		add(cat5);
		
		add(cat11);
		add(cat21);
		add(cat31);
		add(cat41);
		add(cat51);
		
		add(ans11);
		add(ans21);
		add(ans31);
		add(ans41);
		add(ans51);
		
		add(cat12);
		add(cat22);
		add(cat32);
		add(cat42);
		add(cat52);
		
		add(ans12);
		add(ans22);
		add(ans32);
		add(ans42);
		add(ans52);
		
		add(cat13);
		add(cat23);
		add(cat33);
		add(cat43);
		add(cat53);
		
		add(ans13);
		add(ans23);
		add(ans33);
		add(ans43);
		add(ans53);
		
		add(submit1);
		add(submit2);
		add(submit3);
		add(submit4);
		add(submit5);
		
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object src = ae.getSource();
		int value = 100;
		if(src.equals(submit1)){
			stor.addCategory(cat1.getText(), 1);
			stor.put(cat11.getText(), ans11.getText(), 0, 0, value);
			value += 100;
			stor.put(cat12.getText(), ans12.getText(), 0, 1, value);
			value += 100;
			stor.put(cat13.getText(), ans13.getText(), 0, 2, value);
		}
		if(src.equals(submit2)){
			stor.addCategory(cat2.getText(), 1);
			stor.put(cat21.getText(), ans21.getText(), 1, 0, value);
			value += 100;
			stor.put(cat22.getText(), ans22.getText(), 1, 1, value);
			value += 100;
			stor.put(cat23.getText(), ans23.getText(), 1, 2, value);
		}
		if(src.equals(submit3)){
			stor.addCategory(cat3.getText(), 1);
			stor.put(cat31.getText(), ans31.getText(), 2, 0, value);
			value += 100;
			stor.put(cat32.getText(), ans32.getText(), 2, 1, value);
			value += 100;
			stor.put(cat33.getText(), ans33.getText(), 2, 2, value);
		}
		if(src.equals(submit4)){
			stor.addCategory(cat4.getText(), 1);
			stor.put(cat41.getText(), ans41.getText(), 3, 0, value);
			value += 100;
			stor.put(cat42.getText(), ans42.getText(), 3, 1, value);
			value += 100;
			stor.put(cat43.getText(), ans43.getText(), 3, 2, value);
		}
		if(src.equals(submit5)){
			stor.addCategory(cat1.getText(), 1);
			stor.put(cat51.getText(), ans51.getText(), 4, 0, value);
			value += 100;
			stor.put(cat52.getText(), ans52.getText(), 4, 1, value);
			value += 100;
			stor.put(cat53.getText(), ans53.getText(), 4, 2, value);
		}
	}

}
