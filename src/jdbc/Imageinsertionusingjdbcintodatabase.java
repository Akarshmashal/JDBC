package jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Imageinsertionusingjdbcintodatabase 
{
	public static void main(String[] args)throws ClassNotFoundException
	{
		String url =  "jdbc:mysql://localhost:3306 /mydatabase";
		String username = "root";
		String password = "akarsh@@123";
		String image_path = "/Users/akarshmashal/Desktop/image/ANI-20221214145908.jpg";
		String query = "INSERT INTO IMAGE_TABLE(IMAGE_DATA) VALUES(?)"; 

		try
		{
			Class.forName("com.jdbc.cj.mysql.Driver");
			System.out.println("Drivers Loaded");
		}
		catch (ClassNotFoundException e) 
		{
			System.out.println(e.getMessage());
		}

		try
		{
			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Established");
			FileInputStream fileInputStream = new FileInputStream(image_path);//This will convert image fro jpg to binary
			byte[] imageData = new byte[fileInputStream.available()];//Empty array to store image
			fileInputStream.read(imageData);
			PreparedStatement preparedStatement = con.prepareStatement(query); 
			preparedStatement.setBytes(1, imageData);
			int affectedRows = preparedStatement.executeUpdate();
			if(affectedRows > 0)
			{
				System.out.println("Image Inserted Succesfully!! ");
			}
			else
			{
				System.out.println("Image Not Inserted!!");
			}
			
			
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
