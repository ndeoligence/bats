package com.innotec.bats.server.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

public class MySQL_connection
{

	private Properties prop;
	private Connection conn;
	private String username, password, databaseName, url;
	private InputStream is;

	public Connection getConnection ()
	{
		try
		{
			is = new FileInputStream("resources/dbConnection.properties");
			prop = new Properties();
			prop.load(is);
			String dbDriverName = prop.getProperty("dbDriverName");
			username = prop.getProperty("dbUsername");
			password = prop.getProperty("dbPassword");
			databaseName = prop.getProperty("dbName");
			url = prop.getProperty("dbUrl");
			is.close();
			Class.forName(dbDriverName);
			this.conn = DriverManager.getConnection(this.url + databaseName,
					this.username, this.password);
			System.out.println("Connection to db successful");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return this.conn;
	}

}
