package jdbc;

import java.sql.*;

public class PreparedStatement1
{
	public static void main(String[] args)throws ClassNotFoundException
	{
		String url = "jdbc:mysql://localhost:3306/mydatabase";
		String username = "root";
		String password = "akarsh@@123";
		String query = "SELECT * FROM employees WHERE NAME= ? ";
		
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
			preparedStatement.setString(1, "Hemant");
			ResultSet resultSet =preparedStatement.executeQuery();
			while(resultSet.next())
			{
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String job_title = resultSet.getString("job_title");
				double salary  = resultSet.getDouble("salary");
				
				System.out.println();
				System.out.println("ID: " +id);
				System.out.println("NAME: " +name);
				System.out.println("JOB_TITLE: " +job_title);
				System.out.println("SALARY: " +salary);
				
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
