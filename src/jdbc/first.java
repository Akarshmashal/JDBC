package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class first 
{
	public static void main(String[] args) throws SQLException 
	{
		//Database url;

		String url="jdbc:mysql://localhost:3306/students";

		//Database credentials
		String username = "root";
		String password = "akarsh@@123";

		//Establish connection

		try(Connection connection = DriverManager.getConnection(url, username, password) )
		{
			System.out.println("Connection is established");
			System.out.println(connection);
		}
		catch (SQLException e) 
		{
			System.out.println("Connection Failed: " + e.getMessage());	
			
		}
	}
}

