package com.innotec.bats.server.dao;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    public Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
        try {
            InputStream ins = new FileInputStream("src/main/resources/dbConnection.properties");
            Properties properties = new Properties();
            properties.load(ins);

            String dbDriverName = properties.getProperty("dbDriverName"),
                username = properties.getProperty("dbUsername"),
                password = properties.getProperty("dbPassword"),
                dbName = properties.getProperty("dbName"),
                url = properties.getProperty("dbUrl");

            ins.close();
            Class.forName(dbDriverName);

            return DriverManager.getConnection(url + dbName, username, password);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("DBConnection::getConnection() >>" +
                    "\n\tError: " + e);
            throw e;
        }
    }
}
