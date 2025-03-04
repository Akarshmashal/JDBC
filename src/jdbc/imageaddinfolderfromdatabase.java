package jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class imageaddinfolderfromdatabase 
{

	public static void main(String[] args) 
	{
		String url =  "jdbc:mysql://localhost:3306 /mydatabase";
		String username = "root";
		String password = "akarsh@@123";
		String folder_path = "/Users/akarshmashal/Desktop/image/";
		String query = "select image_data from image_table where image_id = (?) "; 

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
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, 1);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				byte[] image_data = resultSet.getBytes("image_data");
				String image_path =folder_path+"Exttracedimage.jpg";
				OutputStream outputStream = new FileOutputStream(image_path);
				outputStream.write(image_data);
				System.out.println("Image Extracted Successfully ");
			}
			else
			{
				System.out.println("Image Notfound");
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
