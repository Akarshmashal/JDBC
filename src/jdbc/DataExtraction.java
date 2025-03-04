package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


// Data extrection 

public class DataExtraction 
{
	public static void main(String[] args) throws ClassNotFoundException 
	{

		String url = "jdbc:mysql://localhost:3306/mydatabase";
		String username = "root";
		String password = "akarsh@@123";
		String query = "SELECT * FROM EMPLOYEES";


		//Loading All MYSQL Drivers

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Drivers Loaded Successfully");

		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println(e.getMessage());
		}

		//Establishing Connection
		try 
		{
			Connection con =DriverManager.getConnection(url, username, password);
			System.out.println("Connection Established Successfully	");

			//Creating Statement
			Statement stmt=con.createStatement();
			//This is for data extraction except this all has to be use executeUpdate
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String job_title = rs.getString("job_title");
				double salary = rs.getDouble("salary");
				System.out.println();
				System.out.println("=========================");
				System.out.println("ID: "+id);
				System.out.println("Name: "+name);
				System.out.println("job_title: "+job_title);
				System.out.println("Salary: "+salary);

			}
			rs.close();
			stmt.close();
			con.close();
			System.out.println();
			System.out.println("Connection Close Successfully");
		} 
		
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}

	}
}
