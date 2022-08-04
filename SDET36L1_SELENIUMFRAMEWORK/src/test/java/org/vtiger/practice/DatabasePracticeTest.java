package org.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabasePracticeTest {

	public static void main(String[] args) throws SQLException {
		Driver driver=new Driver();
		
		DriverManager.registerDriver(driver);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tyss","root","root");
		Statement statement = connection.createStatement();
		int result = statement.executeUpdate("insert into sdet36 values(10006,'Hari1', 9123457799,'hari1@gmail.com','bengaluru')");

		
		
		if(result==1)
		{
			System.out.println("data is  entered");
		}
		else {
			System.out.println("data is not entered");
		}
	}

}
