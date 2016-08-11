package com.innotec.bats.server.dao;

import com.innotec.bats.general.*;
import com.innotec.bats.server.model.BadAccountTypeException;
import com.innotec.bats.server.model.BadTransactionTypeException;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface BatsDAO {
    /**
     * Adds a new account holder onto the database.
     * @param newHolder - the account holder to be added
     * @param sourceId - the Identifier for the teller adding the account.
     * @return true if the account holder was added successfully. Returns false otherwise.
     */
    boolean     addAccountHolder(AccountHolder newHolder, String sourceId);
    boolean     addCurrentAccount(String accountHolderId, CurrentAccount newAccount) throws SQLException;
    boolean     addSavingsAccount(String accountHolderId, SavingsAccount newAccount) throws SQLException;
    boolean     addCreditAccount(String accountHolderId, CreditCardAccount newAccount) throws SQLException;
    boolean     addAccountHolderCard(AccountHolderCard newCard) throws SQLException;
    boolean     addEmployee(Employee newEmployee);
    boolean     addAccount(String accountHolderId, Account account) throws SQLException;
    boolean     addAdminCard(AdminCard card) throws SQLException;
    int         addAtm(String description) throws SQLException;

    AccountHolder       getAccountHolderByCardNo(String cardNo) throws SQLException, BadAccountTypeException;
    AccountHolder       getAccountHolderByIdNo(String idNo) throws SQLException, BadAccountTypeException;
    AccountHolder       getAccountHolderByAccountNo(String accountNo) throws SQLException, BadAccountTypeException;
    AccountHolderCard   getAccountHolderCardByCardNo(String cardNo) throws SQLException;
    AccountHolderCard   getAccountHolderCardByIdNo(String idNo) throws SQLException, BadAccountTypeException;
    AdminCard           getAdminCardByCardNo(String cardNo) throws SQLException;
    AdminCard           getAdminCardByEmployeeNo(String idNo) throws SQLException;
    ATMAdmin            getATMAdminByEmployeeNo(String employeeNo);
    ATMAdmin            getATMAdminByCardNo(String cardNo);
    Employee            getEmployeeByEmployeeNo(String employeeNo);
    Employee            getEmployeeByIdNo(String idNo);
    Account             getAccountByAccountNo(String accountNo) throws SQLException, BadAccountTypeException;
    List<Account>       getAccountsByIdNo(String cardNo) throws SQLException, BadAccountTypeException;
    List<Account>       getAccountsByCardNo(String idNo) throws SQLException, BadAccountTypeException;

    boolean     setAccountBalance(String accountNo, double newBalance) throws SQLException;

    double getAccountBalance(String accountNo) throws SQLException;

    boolean     incrementAccountFunds(String accountNo, double amount) throws SQLException;
    boolean     decrementAccountFunds(String accountNo, double amount) throws SQLException;
    boolean     setAccountHolderPinNo(String cardNo, String newPinNo) throws SQLException;
    boolean     setAdminPinNo(String cardNo, String newPinNo) throws SQLException;

    String getLastAccountNo() throws SQLException;

    String getLastAccountHolderCardNo() throws SQLException;

    boolean closeAccount(String accNo,String tellerId) throws SQLException;
//    boolean createBalanceSheet(String atmId, Date date);
//   boolean updateCardActivity(boolean cardActivity, String cardNo);
//	 boolean updateAccountActivity(boolean activity, String accountNo);
//	 Transaction getTransactionForAccount(String accountID);
    boolean exist(Account account) throws SQLException, BadAccountTypeException;
    boolean exist(AccountHolder accountHolder) throws SQLException, BadAccountTypeException;
    boolean exist(AccountHolderCard card) throws SQLException;
    boolean exist(AdminCard card) throws SQLException;
    boolean logTransaction(Transaction transaction) throws SQLException, BadTransactionTypeException;
    double calculateTransactionCharges(Transaction transaction) throws SQLException, BadTransactionTypeException;

    double getAccountTotalWithdrawnAmountToday(String accountNo) throws SQLException, BadTransactionTypeException;
    boolean removeLastTransaction() throws SQLException;
    boolean blockAccountHolderCard(String cardNo) throws SQLException;
    boolean blockAdminCard(String cardNo) throws SQLException;
    boolean unblockAccountHolderCard(String cardNo, String employeeNo) throws SQLException;
    boolean unblockAdminCard(String cardNo, String employeeNo) throws SQLException;

    /**
     * Logs the calculated charges for a transaction that's just occured.
     * @param transaction - the transaction whose charges are to be logged
     * @param charges - the charges to be logged
     * @return true iff the logging occurred successfully, false otherwise.
     * @throws SQLException - from DB calling methods
     * @throws BadTransactionTypeException
     */
    boolean logTransactionCharges(Transaction transaction, double charges) throws SQLException, BadTransactionTypeException;
}
