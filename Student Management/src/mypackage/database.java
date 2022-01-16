/*Τρίμης Στυλιανός-Αθανάσιος 1564*/


package mypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import mypackage.database;

public class database {
	private String dbURL = "jdbc:mysql://localhost:3306/teddb?serverTimezone=UTC&user=ted&password=ted!";
	private static database instance = null;
	private static Connection dbCon;
	private database() {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch(ClassNotFoundException e) {
				System.out.println("Error in Driver");
			}
			dbCon = DriverManager.getConnection(dbURL);
		} catch(SQLException e) {
			System.out.println("Failed. Please Check Url!");
		}
	}
	
	public static database getInstance() {
		if(database.instance == null) {
			database.instance = new database();
		}
		return database.instance;
	}
	
	public void insert(String i, String n, String ln, int s, String m) {
		String sql = "insert into students(id,first_name,last_name,semester,email) values(?,?,?,?,?)";
		try {
			System.out.println(ln);
			PreparedStatement ps = dbCon.prepareStatement(sql);
			ps.setString(1,i);
			ps.setString(2,n);
			ps.setString(3,ln);
			ps.setInt(4,s);
			ps.setString(5,m);
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public Vector <student> print(){
		Vector <student> students = new Vector<student>();
		try {
			String sql = "select * from students";
			PreparedStatement ps = dbCon.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			String i,name,ln,ml;
			int sem;
			while(rs.next()) {
				i = rs.getString("id");
				name = rs.getString("first_name");
				ln = rs.getString("last_name");
				sem = rs.getInt("semester");
				ml = rs.getString("email");
				student s = new student(i, name, ln, sem, ml);
				students.add(s);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return students;
	}
	public String update(String id, String name, String lastname, int semester, String mail) {
		int rows = 0;
		String sql = "update students set first_name=? where id=?";
		try {
			PreparedStatement ps = this.dbCon.prepareStatement(sql);
			ps.setString(1 , name);
			ps.setString(2, id);
			rows+=ps.executeUpdate();
			ps.close();
			sql="update students set last_name=? where id=?";
			ps=database.dbCon.prepareStatement(sql);
			ps.setString(1, lastname);
			ps.setString(2,id);
			rows+=ps.executeUpdate();
			ps.close();
			sql="update students set email=? where id=?";
			ps=database.dbCon.prepareStatement(sql);
			ps.setString(1,mail);
			ps.setString(2, id);
			rows+=ps.executeUpdate();
			ps.close();
			sql="update students set semester=? where id=?";
			ps=database.dbCon.prepareStatement(sql);
			ps.setInt(1,semester);
			ps.setString(2,id);
			rows+=ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		String message = "Student "+id+":"+name+"-"+lastname+"-"+mail+"-"+String.valueOf(semester);
    	return message;
	}
	
	public String delete(String id) {
		String sql = "delete from students where id=?";
		String msg = "";
		int re = 0;
		try {
			PreparedStatement ps = this.dbCon.prepareStatement(sql);
			ps.setString(1,id);
			re = ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		if (re == 0) {return "";}
		return msg;
	}
	
	public student search(String id) {
		String sql = "select * from students where id=?";
		System.out.println("ID: "+id);
		student search_student = null;
		try {
			PreparedStatement ps = database.dbCon.prepareStatement(sql);
			ps.setString(1,id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if(rs.next()) {
				String name = rs.getString("first_name");
				String ln = rs.getString("last_name");
				int semester = rs.getInt("semester");
				String mail = rs.getString("email");
				search_student = new student(id,name,ln,semester,mail);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return search_student;	
	}
}
