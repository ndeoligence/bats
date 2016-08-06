package com.innotec.bats.server.dao;

import com.innotec.bats.general.*;
import com.innotec.bats.server.model.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BatsDAO_dbImpl implements BatsDAO {
    /*constants*/
    private static final String SAVINGS_ACCOUNT = "Savings Account";
    private static final String CURRENT_ACCOUNT = "Current Account";
    private static final String CREDIT_ACCOUNT = "Credit Card Account";
    /*Prepared Statements*/
                                                    // ID, Name, Surname, Address, ContactNumber
    private static final String ADD_ACCOUNT_HOLDER = "INSERT INTO accountHolders VALUES ('?','?','?','?','?');";
                                                // EmployeeNo,ID,Name,Surname
    private static final String ADD_EMPLOYEE = "INSERT INTO employees VALUES('?','?','?','?');";
    private static final String GET_ACCOUNT_HOLDER_BY_ID_NO = "SELECT * FROM accountHolders WHERE ID='?';";
    private static final String GET_CARD_BY_CARD_NO = "SELECT * FROM accountHolderCards WHERE CardNo='?';";

    private PreparedStatement preparedStatement;
    private Connection connection;
    private ResultSet resultSet;

    public BatsDAO_dbImpl() throws SQLException, IOException, ClassNotFoundException {
        connection = new DBConnection().getConnection();
    }



    public boolean logDeposit(Date date, String accountNo, double amount, String sourceId) {
        return false;
    }
    public boolean logWithdrawal(Date date, String accountNo, double amount, String sourceId) { return false; }

    public String getLastCardNo() {
        return null;
    }

    @Override
    public boolean addEmployee(Employee newEmployee) {
        try {
            preparedStatement = connection.prepareStatement(ADD_EMPLOYEE);
            preparedStatement.setString(1,newEmployee.getEmployeeNo());
            preparedStatement.setString(2,newEmployee.getIdNo());
            preparedStatement.setString(3,newEmployee.getName());
            preparedStatement.setString(4,newEmployee.getSurname());
            return executeUpdateStatement()>0;
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::addEmployee() >>" +
                    "\n\tError..." + e +
                    "\n\tArgument: "+newEmployee);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                System.out.println("BatsDAO_dbImpl::addEmployee() >>" +
                        "\n\tError trying to roll back after an error" +
                        "\n\tNew error: "+e1);
            }
            return false;
        }
    }
    @Override
    public boolean addAccountHolder(AccountHolder newAccountHolder) {
        try {
            preparedStatement = connection.prepareStatement(ADD_ACCOUNT_HOLDER);
            preparedStatement.setString(1,newAccountHolder.getIdNo());
            preparedStatement.setString(2,newAccountHolder.getName());
            preparedStatement.setString(3,newAccountHolder.getSurname());
            preparedStatement.setString(4,newAccountHolder.getAddress());
            preparedStatement.setString(5,newAccountHolder.getContactNo());
            return (executeUpdateStatement() > 0);
        } catch (SQLException e) {
            System.out.println("BatsDAO_dbImpl::addAccountHolder() >>" +
                    "\n\tError..." + e +
                    "\n\tArgument: "+newAccountHolder);

            try {
                connection.rollback();
            } catch (SQLException e1) {
                System.out.println("BatsDAO_dbImpl::addAccountHolder() >>" +
                        "\n\tError trying to roll back after an error" +
                        "\n\tNew error: "+e1);
            }
            return false;
        }
    }
    @Override
    public AccountHolder getAccountHolderByIdNo(String idNo) throws SQLException, BadAccountTypeException {
        preparedStatement=connection.prepareStatement(GET_ACCOUNT_HOLDER_BY_ID_NO);
        preparedStatement.setString(1,idNo);
        resultSet=preparedStatement.executeQuery();

        if (!resultSet.next()) {
            return null;
        }

        String name,surname,address,contactNo;
        Card card;
        List<Account> accounts = new ArrayList<>();

        idNo=resultSet.getString("ID");
        name=resultSet.getString("Name");
        surname=resultSet.getString("Surname");
        address=resultSet.getString("Address");
        contactNo=resultSet.getString("ContactNumber");
        resultSet.close();

        card = getAccountHolderCardByCardNo(getCardNoByIdNo(idNo));

        AccountHolder accountHolder = new AccountHolder(name,surname,idNo,address,contactNo,card);
        accountHolder.setAccounts((ArrayList<Account>) getAccountsByCardNo(idNo));
        return accountHolder;
    }
    @Override
    public AccountHolder getAccountHolderByCardNo(String cardNo) throws SQLException, BadAccountTypeException {
        return getAccountHolderByIdNo(getCardNoByIdNo(cardNo));
    }
    @Override
    public List<Account> getAccountsByIdNo(String idNo) throws SQLException, BadAccountTypeException {
        return getAccountsByCardNo(getCardNoByIdNo(idNo));
    }
    @Override
    public List<Account> getAccountsByCardNo(String cardNo) throws SQLException, BadAccountTypeException {
        List<Account> accounts = new ArrayList<>();
        preparedStatement=connection.prepareStatement(GET_CARD_BY_CARD_NO);
        resultSet=preparedStatement.executeQuery();
        String accountNo,idNo;
        double balance,maxWithdrawalPerDay,maxTransferPerDay;
        boolean active;
        while (resultSet.next()) {

            accountNo=resultSet.getString("AccountNo");
            idNo=getIdNoByCardNo(cardNo);
            balance=resultSet.getDouble("Balance");
            maxWithdrawalPerDay=resultSet.getDouble("MaxWithdrawalPerDay");
            maxTransferPerDay=resultSet.getDouble("MaxTransferPerDay");
            active=resultSet.getBoolean("Active");

            switch (resultSet.getString("Type")) {
                case (CURRENT_ACCOUNT):
                    accounts.add(new CurrentAccount(accountNo,balance,active,maxWithdrawalPerDay,maxTransferPerDay,idNo));
                    break;
                case (SAVINGS_ACCOUNT):
                    accounts.add(new SavingsAccount(accountNo,balance,active,maxWithdrawalPerDay,maxTransferPerDay,idNo));
                    ((SavingsAccount)accounts.get(accounts.size()-1)).setWithdrawalPending(resultSet.getBoolean("WithdrawalPending"));
                    break;
                case (CREDIT_ACCOUNT):
                    accounts.add(new CreditCardAccount(accountNo,balance,active,maxWithdrawalPerDay,maxTransferPerDay,idNo));
                    break;
                default:
                    throw new BadAccountTypeException("Unexpected account type: "+resultSet.getString("Type"));
            }
        }
        return accounts;
    }
    @Override
    public Card getAccountHolderCardByCardNo(String cardNo) throws SQLException {
        preparedStatement=connection.prepareStatement(GET_CARD_BY_CARD_NO);
        resultSet=preparedStatement.executeQuery();
        if (!resultSet.next())
            return null;
        String pinNo = resultSet.getString("PIN");
        boolean active = resultSet.getBoolean("Active");
        String idNo = resultSet.getString("AccountHolderID");
        return new AccountHolderCard(cardNo,pinNo,active,idNo);
    }


    private String getCardNoByIdNo(String idNo) {
        return null;
    }
    private String getIdNoByCardNo(String cardNo) {
        return null;
    }
    private int executeUpdateStatement() throws SQLException {
        connection.setAutoCommit(false);
        int affected = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.setAutoCommit(true);
        return affected;
    }
}
