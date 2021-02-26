package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2jdbcReadDemo {
	// JDBC driver name and database URL 
	final String JDBC_DRIVER = "org.h2.Driver";   
	final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
	
	//  Database credentials 
	final String USER = "sa"; 
	final String PASS = "";
	
	private Connection conn;
	private Statement stmt;
	
	public Statement getStmt() throws ClassNotFoundException, SQLException {
		// STEP 1: Register JDBC driver 
		Class.forName(JDBC_DRIVER); 
		
		//STEP 2: Open a connection 
		System.out.println("Connecting to a selected database..."); 
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		System.out.println("Connected database successfully...");
		
		//STEP 3: Execute a query
		stmt = conn.createStatement();
		return stmt;
	}
	
	public boolean login(String username, String password) {
		Statement stmt = null;
		try {
			stmt = getStmt();
			String sql = "SELECT id, username, password "
					+ "FROM member "
					+ "WHERE username = '"+ username + "' "
					+ "AND password = '"+ password + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next() == false) {
				System.out.println("user does not exist...");
				return false;
			}
			
			int id = rs.getInt("id");
			String findUsername = rs.getString("username");
			String findPassword = rs.getString("password");
			
			// Display values
			System.out.print("ID : " + id);
			System.out.print(", Username: " + findUsername);
			System.out.println(", Password: " + findPassword);
			
			if(findUsername != null) {
				System.out.println("login success!!");
				return true;
			} else {
				return false;
			}
		} catch(Exception e) { 
			e.printStackTrace(); 
		} finally {
			try{
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch(SQLException se2) { 
				
			}
		}
		
		System.out.println("login failed...");
		return false;
	}
	
	public void argRun(String keyword) {
		Statement stmt = null;
		try {
			stmt = getStmt();
			String sql = "SELECT id, username, password FROM member where username = '"+ keyword + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			rs.next();
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			
			// Display values
			System.out.print("ID : " + id);
			System.out.print(", Username: " + username);
			System.out.println(", Password: " + password);
		} catch(Exception e) { 
			e.printStackTrace(); 
		} finally {
			try{
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch(SQLException se2) { 
				
			}
		}
		
		System.out.println("Goodbye!");
	}
	
	public void run() { 
		Statement stmt = null;
		try {
			stmt = getStmt();
			String sql = "SELECT id, username, password FROM member";
			ResultSet rs = stmt.executeQuery(sql);
			
			// STEP 4 : Extract data from result set
			while(rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				// Display values
				System.out.print("ID : " + id);
				System.out.print(", Username: " + username);
				System.out.print(", Password: " + password);
			}
			
			// STEP 5 : Clean-up environment 
			stmt.close(); 
			conn.close();
		} catch(Exception e) { 
			e.printStackTrace(); 
		} finally {
			try{
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch(SQLException se2) { 
				
			}
		}
		
		System.out.println("Goodbye!");
	} 
}
