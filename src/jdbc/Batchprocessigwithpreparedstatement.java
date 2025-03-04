package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Batchprocessigwithpreparedstatement 
{

	public static void main(String[] args) 
	{
		String url = "jdbc:mysql://localhost:3306/mydatabase";
		String username = "root";
		String password = "akarsh@@123";

		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Drivers Loaded!!");
		}
		catch (ClassNotFoundException e) 
		{
			System.out.println(e.getMessage());
		}

		try
		{
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Established!!");
			connection.setAutoCommit(false);
			String query = ("INSERT INTO employees(name, job_title, salary) VALUES(?, ?, ?)");
			PreparedStatement preparedStatement = connection.prepareStatement(query); 
			Scanner scanner = new Scanner(System.in);
			while(true)
			{
				 System.out.println("Name: ");
				 String name = scanner.nextLine();
				 System.out.println("Job_title: ");
				 String Job_title = scanner.nextLine();
				 System.out.println("Salary: ");
				 double salary = scanner.nextDouble();
				 preparedStatement.setString(1, name);
				 preparedStatement.setString(2, Job_title);
				 preparedStatement.setDouble(3, salary);
				 scanner.nextLine();
				 preparedStatement.addBatch(); 
				 System.out.println("Add More Values Y/N: ");
				 String decision = scanner.nextLine();
				 if(decision.toUpperCase().equals("N"))
				 {
					 break;
				 }
			}
			
			int [] batchResults = preparedStatement.executeBatch();
			connection.commit();
			System.out.println("Batch Executed Successfully!!");
			
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}
	}

	}
