package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionHandling 
{

	public static void main(String[] args) throws ClassNotFoundException
	{
		String url = "jdbc:mysql://localhost:3306/mydatabase";
		String username = "root";
		String password = "akarsh@@123";
		String withdrawQuery = "UPDATE ACCOUNTS SET BALANCE = BALANCE - ? WHERE ACCOUNT_NUMBER = ?";
		String depositQuery = "UPDATE ACCOUNTS SET BALANCE = BALANCE +  ? WHERE ACCOUNT_NUMBER = ?";

		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Drivers Loaded successfully!!");
		}
		catch (ClassNotFoundException e) 
		{
			System.out.println(e.getMessage());
		}

		try
		{
			Connection con  = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Established!!");
			con.setAutoCommit(false);

			try
			{
				PreparedStatement withdrawStatement = con.prepareStatement(withdrawQuery);
				PreparedStatement depositStatement = con.prepareStatement(depositQuery);
				withdrawStatement.setDouble(1, 500.00);
				withdrawStatement.setString(2, "account456");
				depositStatement.setDouble(1, 500.00);
				depositStatement.setString(2, "account4567857657");
				int rowsaffectedwithdraw = withdrawStatement.executeUpdate();
				int rowsaffecteddeposit = depositStatement.executeUpdate();
				if(rowsaffectedwithdraw > 0 && rowsaffecteddeposit > 0)
				{
					con.commit(); 
					System.out.println("Transaction Successfull!!");
				}
				else
				{
					con.rollback();
					System.out.println("Transaction Falied!!");
				}
			}
			catch (SQLException e) 
			{
				System.out.println(e.getMessage());
			}		
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}

	}

}
