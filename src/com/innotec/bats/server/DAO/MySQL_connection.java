package innotec.bats.server.dao;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

public class MySQL_connection {

	private Properties prop;
	private Connection conn;
	private String username, password, databaseName, url;
	private InputStream is;
	
	public Connection getConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
				is = new FileInputStream("resources/DatabaseConnection.properties");
				prop = new Properties();
				prop.load(is);
				username = prop.getProperty("username");
				password = prop.getProperty("password");
				databaseName = prop.getProperty("database");
				url = prop.getProperty("url");
				is.close();
				this.conn = DriverManager.getConnection(this.url+databaseName, this.username, this.password);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return this.conn;
	}
	
}
