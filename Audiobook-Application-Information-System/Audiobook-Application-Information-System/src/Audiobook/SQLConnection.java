package Audiobook;

import java.sql.*;

public class SQLConnection {
		
		public  Connection getConnection() throws Exception {
			String url = "jdbc:postgresql://localhost:5432/Audiobook";
			String userName = "postgres";
			String password = "1234";
			Connection connection = null;
			
			try {
				connection = DriverManager.getConnection(url,userName,password);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			return connection;
		}
		
		
	
		


}
