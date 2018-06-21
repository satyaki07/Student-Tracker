package com.satyaki07.web.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {
	
	private DataSource dataSource;
	
	public StudentDbUtil(DataSource theDataSource) {
		
		dataSource = theDataSource;
		
	}
	
	public List<Student> getStudents() throws Exception {
		
		List<Student> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRes = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "select * from student order by last_name";
			
			myStmt = myConn.createStatement();
			
			myRes = myStmt.executeQuery(sql);
			
			while(myRes.next()) {
				
				int id = myRes.getInt("id");
				
				String firstName = myRes.getString("first_name");
				
				String lastName = myRes.getString("last_name");
				
				String email = myRes.getString("email");
				
				Student tempStudent = new Student(id, firstName, lastName, email);
				
				students.add(tempStudent);
				
			}
			
			return students;	
			
		}
		
		
		finally {
			
			close(myConn,myStmt,myRes);
		}
		
		

		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRes) {
		 
		try {
			
			if(myRes != null) {
				myRes.close();
			}
			
			if(myStmt != null) {
				myStmt.close();
			}
			
			if(myConn != null) {
				myConn.close();
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
