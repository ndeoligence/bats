package com.innotec.bats.server.dao;

import com.innotec.bats.general.*;
import com.innotec.bats.server.model.BadAccountTypeException;

import java.sql.SQLException;
import java.util.List;

public interface BatsDAO {

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
    AccountHolderCard   getAccountHolderCardByIdNo(String idNo) throws SQLException;
    AdminCard           getAdminCardByCardNo(String cardNo) throws SQLException;
    AdminCard           getAdminCardByEmployeeNo(String idNo) throws SQLException;
    Account             getAccount(String accountNo) throws SQLException, BadAccountTypeException;
    List<Account>       getAccountsByIdNo(String cardNo) throws SQLException, BadAccountTypeException;
    List<Account>       getAccountsByCardNo(String idNo) throws SQLException, BadAccountTypeException;

    boolean     transferFunds(String accountNo_source, String accountNo_dest);
    boolean     setAccountBalance(String accountNo, double newBalance) throws SQLException;
    boolean     setAccountHolderPinNo(String cardNo, String newPinNo) throws SQLException;
    boolean     setAdminPinNo(String cardNo, String newPinNo) throws SQLException;

    String getLastCardNo() throws SQLException;


//    boolean processWithdrawal(Withdrawal newWithdrawal);
//    boolean processDeposit(String account, double amount);
//    boolean processTransfer(Transfer newTransfer);
//    boolean changePIN(String newPIN, String cardNo);
//    AdminCard getAdminCard(String cardNo);
//
//    boolean closeAccount(String accNo);
//
//    boolean deactivateCard(String cardNo);
//
//    boolean reactivateCard(String cardNo);
//
//    boolean createBalanceSheet(String atmId, Date date);

//	 CreditCardAccount getCreditCardAccount(String accountHolderIdNo);

    //	 boolean updateCardActivity(boolean cardActivity, String cardNo);
//	 boolean updateAccountActivity(boolean activity, String accountNo);
//	 Employee getEmployee(String employeeID);
//	 Transaction getTransactionForAccount(String accountID);
//    boolean logDeposit(java.util.Date dateTime, String accountNo, double amount);
}
