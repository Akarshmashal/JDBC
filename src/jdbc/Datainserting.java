package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Datainserting 
{
	public static void main(String[] args) throws ClassNotFoundException 
	{

		String url = "jdbc:mysql://localhost:3306/mydatabase";
		String username = "root";
		String password = "akarsh@@123";
		String query = "insert into employees(id, name, job_title, salary) values( 4 , 'Ujjawal', 'React Developer', 77000.0);";


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
				System.out.println("Insert Successfully " + rowsaffected + "  row(s) affected");
			}
			else
			{
				System.out.println("Insertion Failed");
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
