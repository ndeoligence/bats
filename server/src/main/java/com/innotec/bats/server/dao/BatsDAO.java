package com.innotec.bats.server.dao;

import com.innotec.bats.general.*;
import com.innotec.bats.server.model.BadAccountTypeException;

import java.sql.SQLException;
import java.util.List;

public interface BatsDAO {

    boolean addAccountHolder(AccountHolder newHolder);
//    boolean addCurrentAccount(String accountHolderId, CurrentAccount account);
//    boolean addSavingsAccount(String accountHolderId, SavingsAccount account);
//    boolean addAccountHolderCard(AccountHolderCard newCard);

    boolean addEmployee(Employee newEmployee);

    AccountHolder getAccountHolderByCardNo(String cardNo) throws SQLException, BadAccountTypeException;

    List<Account> getAccountsByIdNo(String cardNo) throws SQLException, BadAccountTypeException;

    List<Account> getAccountsByCardNo(String idNo) throws SQLException, BadAccountTypeException;

    Card getAccountHolderCardByCardNo(String cardNo) throws SQLException;

    AccountHolder getAccountHolderByIdNo(String idNo) throws SQLException, BadAccountTypeException;

//	 void addAdminCard(AdminCard newAdmin);
//	 void addAccountHolderCard(AccountHolderCard newCard);
//	 void addCreditCardAccount(String accountHolderId, CreditCardAccount account);

//    boolean processWithdrawal(Withdrawal newWithdrawal);
//
//    boolean processDeposit(String account, double amount);
//
//    boolean processTransfer(Transfer newTransfer);
//
//    boolean changePIN(String newPIN, String cardNo);
//
//    AdminCard getAdminCard(String cardNo);
//
//    AccountHolderCard getAccountHolderCard(String cardNo);
//
//    AccountHolder getAccountHolderByCardNo(String cardNo) throws SQLException;
//
//    AccountHolder getAccountHolderByIdNo(String idNo);
//
//    AccountHolder getAccountHolderByAccountNo(String accNo);
//
//    ArrayList<Account> getAccounts(String cardNo);
//
//    boolean closeAccount(String accNo);
//
//    boolean deactivateCard(String cardNo);
//
//    boolean reactivateCard(String cardNo);
//
//    ArrayList<Transaction> getStatement(String accNo);
//
//    boolean createBalanceSheet(String atmId, Date date);

//	 CurrentAccount getCurrentAccount(String accountHolderIdNo);
//	 SavingsAccount getSavingsAccount(String accountHolderIdNo);

//	 CreditCardAccount getCreditCardAccount(String accountHolderIdNo);
//	 List<Account> getAccounts(String accountHolderIdNo);

    //	 boolean updateCardActivity(boolean cardActivity, String cardNo);
//	 boolean updateAccountActivity(boolean activity, String accountNo);
//	 AccountHolderCard getAccountHolderCardByIdNo(String idNo);		
//	 AdminCard getAdminCardById(String idNo);
//	 Employee getEmployee(String employeeID);
//	 Transaction getTransactionForAccount(String accountID);
//	 AccountHolderCard getAccountHolderCardByCardNo(String cardNo);
//    boolean logDeposit(java.util.Date dateTime, String accountNo, double amount);

//    String getLastCardNo();
}
