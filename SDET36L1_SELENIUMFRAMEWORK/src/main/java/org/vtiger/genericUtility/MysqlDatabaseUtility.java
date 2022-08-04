package org.vtiger.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

/**
 *This class contains all the reusable methods for database 
 * @author avula
 *
 */

public final class MysqlDatabaseUtility {

	private Driver driver;
	private Connection connection;
	private Statement statement; 
	
	/**
	 * This method is used to establish the Database connection
	 * @param DB_URL
	 * @param username
	 * @param password
	 */

	public void establishDataConnection(String DB_URL, String username, String password)
	{
		try {
			driver=new Driver();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			DriverManager.registerDriver(driver);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(DB_URL, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method is used to fetch the data from Database
	 * @param sqlSelectQuery
	 * @return
	 */

	public ResultSet getDataFromDataBase(String sqlSelectQuery)
	{
		ResultSet result=null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try 
		{
			result = statement.executeQuery(sqlSelectQuery);
			System.out.println("Data Entered into Database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * This method is used to update the data into Database
	 * @param sqlUpdateQuery
	 * @return
	 */

	public int updateDataIntoDatabase(String sqlUpdateQuery)
	{
		int result =0;
		try {
			statement=connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			result = statement.executeUpdate(sqlUpdateQuery);
			System.out.println("Data Entered into DataBase");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * This method is used to close the Connection
	 */

	public void closeDataBaseConnection() 
	{
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}


