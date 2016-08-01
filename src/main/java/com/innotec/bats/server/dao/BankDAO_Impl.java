package com.innotec.bats.server.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.*;
import java.sql.Statement;
//import org.mariadb.jdbc.Driver;

import com.innotec.bats.general.*;

/**
 * Created by phoenix on 7/24/16.
 */
public class BankDAO_Impl implements BankDAO {
    private static final String PROPERTIES_FILENAME = "resources/dbConnection.properties";
    Connection connection;
    public BankDAO_Impl() throws IOException, SQLException {
        String username, pw, dbUrl, dbName, dbDriverName;
        Properties props = new Properties();
        try {
            props.load(new FileReader(PROPERTIES_FILENAME));
            username = props.getProperty("dbUsername");
            pw = props.getProperty("dbPassword");
            dbUrl = props.getProperty("dbUrl");
            dbName = props.getProperty("dbName");
            dbDriverName = props.getProperty("dbDriverName");

            System.out.println("Connecting to DB...");
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl+dbName,username,pw);
            System.out.println("Done!");
        } catch (IOException e) {
            System.err.println("Failed! (Due to exception: " + e + ")");
            throw e;
        } catch (SQLException e) {
            System.err.println("Failed: Due to exception " + e);
            throw e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Card getAdminCard(String cardNo) {
        PreparedStatement pStatement=null;
        ResultSet resultSet=null;
        /*toDo: get card from db*/
        try {
            String query = "SELECT * FROM accountHolderCards WHERE CardNo='"+ cardNo +"';";
            pStatement = connection.prepareStatement(query);
//            pStatement.setString(1, cardNo);
            resultSet = pStatement.executeQuery();
            AdminCard card = row2adminCard(resultSet);
            card.setEmployeeNo("");
            return card;
        } catch (SQLException e) {
            System.err.println("BanksDAO_Impl::getCard()\tError: " + e + "\n\tReturn null");
            return null;
        }
    }
    @Override
    public Card getAccountHolderCard(String cardNo) {
        PreparedStatement pStatement;
        ResultSet resultSet;
        /*toDo: get card from db*/
        try {
            String SEL_ACC_HOLDER_CARD_BY_CARD_NO = "SELECT * FROM accountHolderCards WHERE cardNo='"+cardNo+"';";
            pStatement = connection.prepareStatement(SEL_ACC_HOLDER_CARD_BY_CARD_NO);
//            pStatement.setString(1,cardNo);
            resultSet = pStatement.executeQuery();
            return row2accountHolderCard(resultSet);
        } catch (SQLException e) {
            System.err.println("BanksDAO_Impl::getCard()\tError: " + e + "\n\tReturn null");
            return null;
        }
    }
    @Override
    public Account getAccount(String accountNo) {
        Account account = null;
        /*toDo: get account from db*/
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("");
            /*look for account within currentAccount table*/
            if (resultSet.next()) {
                do {

                } while (resultSet.next());
            }
            /*look for account within savingsAccount table*/
            if (resultSet.next()) {
                do {

                } while (resultSet.next());
            }
            /*look for account within creditAccount table*/
            if (resultSet.next()) {
                do {

                } while (resultSet.next());
            }
        } catch (SQLException e) {
            System.err.println("BankDAO_Impl::getAccount(accountNo) >> Exception: " + e);
        }

        return account;
    }
    @Override
    public List<Account> getAccountsByIdNo(String idNo) {
        List<Account> accounts = new ArrayList<>();
        Statement statement;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM employees;");
            while (resultSet.next()) {
                accounts.add(row2account(resultSet));
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

        return null;
    }
    @Override
    public List<Account> getAccountsByCardNo(String cardNo) {
        List<Account> accounts = new ArrayList<>();
        Account account = null;
        /*toDo: get account from db*/
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("");
            /*look for account within currentAccount table*/
            if (resultSet.next()) {
                do {
                    accounts.add(row2currentAccount(resultSet));
                } while (resultSet.next());
            }
            /*look for account within savingsAccount table*/
            if (resultSet.next()) {
                do {

                } while (resultSet.next());
            }
            /*look for account within creditAccount table*/
            if (resultSet.next()) {
                do {

                } while (resultSet.next());
            }
        } catch (SQLException e) {
            System.err.println("BankDAO_Impl::getAccount(accountNo) >> Exception: " + e);
        }

        return accounts;
    }

    private CurrentAccount row2currentAccount(ResultSet resultSet) {
        String accountNo;
        double balance,minBalance,maxWithdrawalPerDay,maxTransferPerDay;
        boolean active;
        CurrentAccount account;
        try {
            accountNo=resultSet.getString("accountNo");
            balance=resultSet.getDouble("balance");
            active=resultSet.getBoolean("active");
            minBalance=resultSet.getDouble("minBalance");
            maxWithdrawalPerDay=resultSet.getDouble("maxWithdrawalPerDay");
            maxTransferPerDay=resultSet.getDouble("maxTransferPerDay");
            account = new CurrentAccount(accountNo,balance,active,minBalance,maxWithdrawalPerDay,maxTransferPerDay);
        } catch (SQLException e) {
            System.err.println("BankDAO_Impl::row2currentAccount(resultSet) >> Exception: " + e);
            account = null;
        }
        return account;
    }
    private SavingsAccount row2savingsAccount(ResultSet resultSet) {
        SavingsAccount account=null;
        return account;
    }
    private CreditCardAccount row2creditCardAccount(ResultSet resultSet) {
        CreditCardAccount account=null;
        return account;
    }

    private Account row2account(ResultSet rSet) throws SQLException {
        String name=null, email=null;
        double salary=0;
        Account account = null;

        /*toDo: get Account from db*/

        return account;
    }

    private AdminCard row2adminCard(ResultSet rSet) throws SQLException {
        AdminCard card;
        String cardNo; String pinNo; boolean active; String employeeNo;
        /*toDo: get admin card from db*/
        if (rSet.next()) {
            cardNo = rSet.getString("CardNo");
            pinNo = rSet.getString("Card_PIN");
            active = rSet.getBoolean("Card_Active");
            employeeNo = null;
            card = new AdminCard(cardNo,pinNo,active,employeeNo);
        } else {
            card = null;
            System.out.println("The card doesn't seem to exist.\n\tReturning Null");
        }

        return card;
    }
    private AccountHolderCard row2accountHolderCard(ResultSet rSet) throws SQLException {
        AccountHolderCard card;
        String cardNo; String pinNo; boolean active, blocked; List<String> accountNos;

        /*toDo: get acc holder card from db*/
        if (rSet.next()) {
            cardNo = rSet.getString("cardNo");
            pinNo = rSet.getString("pinNo");
            active = rSet.getBoolean("active");
//            blocked = rSet.getBoolean("blocked");

            card = new AccountHolderCard(cardNo,pinNo,active);
        } else {
            System.out.println("The Account Holder card doesn't seem to exist.\n\tReturning Null");
            return null;
        }
        return card;
    }
}
