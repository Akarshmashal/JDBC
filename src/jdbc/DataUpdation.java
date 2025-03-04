package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataUpdation 
{
	public static void main(String[] args) throws ClassNotFoundException 
	{

		String url = "jdbc:mysql://localhost:3306/mydatabase";
		String username = "root";
		String password = "akarsh@@123";
		String query = "UPDATE employees set job_title = 'FullStack Developer', salary = 70000 where id = 2";


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
			int rowsaffected = stmt.executeUpdate(query);
			
			if(rowsaffected > 0)
			{
				System.out.println("Update Successfully " + rowsaffected + "  row(s) affected");
			}
			else
			{
				System.out.println("Update Failed");
			}

		
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
