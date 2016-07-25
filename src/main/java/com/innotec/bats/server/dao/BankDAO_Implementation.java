package com.innotec.bats.server.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.innotec.bats.general.*;

/**
 * Created by phoenix on 7/24/16.
 */
public class BankDAO_Implementation implements BankDAO {
    Connection connection;
    public BankDAO_Implementation() throws SQLException {
        String username=null,
               pw=null,
               dbUrl=null,
                driver_fullName,
               dbName=null;
        Properties props = new Properties();
        try {
            props.load(new FileReader("src/main/resources/dbConnection.properties"));
            username = props.getProperty("username");
            pw = props.getProperty("password");
            dbUrl = props.getProperty("url");
            dbName = props.getProperty("db_name");
            driver_fullName = props.getProperty("driver_fullName");

            System.out.println("Connecting to DB...");
//            Class.forName(driver_fullName);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } /*catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }*/

        connection = DriverManager.getConnection(dbUrl+dbName,username,pw);
        System.out.println("Done!");
    }

    @Override
    public Card getCard(String cardNo) {

    }
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        Statement statement;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM employees;");
            while (resultSet.next()) {
                employees.add(row2employee(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employees;
    }

    @Override
    public List<Employee> search(String lastName) {
        return null;
    }

    public Employee row2employee(ResultSet rSet) {
        String name=null, surname=null, email=null, department=null;
        double salary=0;

        try {
            name=rSet.getString("last_name");
            surname=rSet.getString("first_name");
            email=rSet.getString("email");
            department=rSet.getString("department");
            salary=rSet.getDouble("salary");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Employee(surname,name,email,department,salary);
    }
}
