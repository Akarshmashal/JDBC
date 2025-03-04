package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class preparedStatement4 
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
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter ID");
			int id = sc.nextInt();
			sc.nextLine();
			System.out.println("Enter Name");
			String name = sc.nextLine();
			System.out.println("Enter job_title");
			String job_title = sc.nextLine();
			System.out.println("Enter Salary");
			double salary = sc.nextDouble();
			
			//Statement statement = con.createStatement();
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
			preparedStatement.setInt(1,id);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, job_title);
			preparedStatement.setDouble(4, salary);
			
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
