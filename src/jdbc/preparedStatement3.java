package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class preparedStatement3 
{
	public static void main(String[] args)throws ClassNotFoundException
	{
		String url = "jdbc:mysql://localhost:3306/mydatabase";
		String username = "root";
		String password = "akarsh@@123";
		String query = "INSERT INTO employees(id, name, job_title, salary) VALUES(?, ?, ?, ?)";
		
		//Loading drivers
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Drivers Loaded");
		}
		catch (ClassNotFoundException e) 
		{
			System.out.println(e.getMessage());
		}
		
		//Connection Establishment
		
		try
		{
			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Established");
			//Statement statement = con.createStatement();
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
			preparedStatement.setInt(1,3);
			preparedStatement.setString(2, "Akarsh");
			preparedStatement.setString(3, "Devops Engineer");
			preparedStatement.setDouble(4, 850000.0);
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			if(rowsAffected > 0)
			{
				System.out.println("Data Inserted Successfully");
			}
			else
			{
				System.out.println("Incorrect Data");
			}
			
			con.close();
			System.out.println();
			System.out.println("Connection closed Successfully");
			
		}
		
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}
