package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

//this class helps in connection with Database
public class StudentDbUtil {
	
	private DataSource dataSource;
	
	//set up constructor, creates StrudentDbUtil and passes a reference to our DataSource object
	// actually our Servlet will do this
	public StudentDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	// method that lists the students
	public List<Student> getStudents() throws Exception {
		
		List<Student> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt= null;
		ResultSet myRs = null;
		
		
		try {
		// get a connection from connection pool
		myConn = dataSource.getConnection();
		
		// create a sql statement
		String sql = "select * from student order by last_name";
		
		myStmt = myConn.createStatement();
		
		// execute query
		myRs = myStmt.executeQuery(sql);
		
		// process result set
		while (myRs.next()) {
			
			// retrieve data from result set row
			int id = myRs.getInt("id"); //id - column name
			String firstName = myRs.getString("first_name");
			String lastName = myRs.getString("last_name");
			String email = myRs.getString("email");
			
			// create new student object
			Student tempStudent = new Student(id, firstName, lastName, email);
			
			// add it to list of students
			students.add(tempStudent);
			
		}
		
		// close JDBC objects
		close(myConn, myStmt, myRs);
		
			
		return students;
		
		}
		finally {
			
		}

	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		try {
			if (myRs != null) {
				myRs.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
			// it doesn't really close database connection, puts it back to connection pool
			// makes it available for someone else to use
			if (myConn != null) { 
				myConn.close();			// execute sql statement

			}
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}

	public void addStudent(Student theStudent) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into student "
						+ "(first_name, last_name, email) "
						+ "values (?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			//set param values for the student
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
		// clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
	}

	public Student getStudent(String theStudentId) throws Exception {
		
		Student theStudent = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try {		
			
			//convert student id to int
			studentId = Integer.parseInt(theStudentId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from student where id=?"; 
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			
			// set params
			myStmt.setInt(1, studentId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if(myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				// use the studentId during construction
				theStudent = new Student(studentId, firstName, lastName, email);
			}
			else {
				throw new Exception("Could not find student id: " + studentId);
			}
			
			
			
		return theStudent;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
		}

	public void updateStudent(Student theStudent) throws Exception{

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update student "
						+ "set first_name=?, last_name=?, email=? "
						+ "where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteStudent(String theStudentId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			//convert studentId to int
			int studentId = Integer.parseInt(theStudentId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student 
			String sql = "delete from student where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, studentId);
			
			// execute sql statement
			myStmt.execute();
			
			
		}
		finally {
			//clean up JDBC code
			close(myConn, myStmt, null);
		}
		
		
	}
	
}
	












