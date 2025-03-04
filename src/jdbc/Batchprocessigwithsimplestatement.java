package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Batchprocessigwithsimplestatement
{

	public static void main(String[] args)throws ClassNotFoundException , SQLException 
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
			Statement statement = connection.createStatement();
			statement.addBatch("INSERT INTO employees (id, name, job_title, salary) VALUES(5, 'Vashu', 'HR Manager', 65000.0)");
			statement.addBatch("INSERT INTO employees (id, name, job_title, salary) VALUES(6, 'Karan', 'Cyber Security Analyst', 62000.0)");
			statement.addBatch("INSERT INTO employees (id, name, job_title, salary) VALUES(7, 'Vipul', 'Devops Engineer', 67000.0)");
			int [] batchresult = statement.executeBatch();
			connection.commit();
			System.out.println("Batch Executed Successfully!! ");
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}
	}

}
