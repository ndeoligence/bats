package com.innotec.bats.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.innotec.bats.general.*;

public interface DAO_Interface
{

	public boolean addAccountHolder(AccountHolder newHolder, String tellerId);	
	public boolean addCurrentAccount(String accountHolderId, CurrentAccount account);
	public boolean addSavingsAccount(String accountHolderId, SavingsAccount account);
	public boolean addAccountHolderCard(AccountHolderCard newCard);
	
	public boolean processWithdrawal(Withdrawal newWithdrawal);
	public boolean processDeposit(String account, double amount);
	public boolean processTransfer(Transfer newTransfer);

	public boolean changePIN(String newPIN, String cardNo);
	
	public AdminCard getAdminCard (String cardNo);
	public AccountHolderCard getAccountHolderCard (String cardNo);
	
	public AccountHolder getAccountHolderByCardNo(String cardNo) throws SQLException;
	public AccountHolder getAccountHolderByIdNo(String idNo);
	public AccountHolder getAccountHolderByAccountNo(String accNo);
	
	public ArrayList<Account> getAccounts (String cardNo);
	
	public boolean closeAccount(String accNo);
	public boolean deactivateCard(String cardNo);
	public boolean reactivateCard(String cardNo);
	
	public ArrayList<Transaction>getStatement(String accNo);
	
	public boolean createBalanceSheet(String atmId, Date date);
	
//	public CurrentAccount getCurrentAccount(String accountHolderIdNo);
//	public SavingsAccount getSavingsAccount(String accountHolderIdNo);

//	public CreditCardAccount getCreditCardAccount(String accountHolderIdNo);
//	public List<Account> getAccounts(String accountHolderIdNo);
	
//	public void addEmployee(Employee newEmployee);
//	public void addAdminCard(AdminCard newAdmin);
//	public void addAccountHolderCard(AccountHolderCard newCard);
//	public void addCreditCardAccount(String accountHolderId, CreditCardAccount account);
//	public boolean updateCardActivity(boolean cardActivity, String cardNo);
//	public boolean updateAccountActivity(boolean activity, String accountNo);
//	public AccountHolderCard getAccountHolderCardByIdNo(String idNo);		
//	public AdminCard getAdminCardById(String idNo);
//	public Employee getEmployee(String employeeID);
//	public Transaction getTransactionForAccount(String accountID);
//	public AccountHolderCard getAccountHolderCardByCardNo(String cardNo);
	public boolean logDeposit(java.util.Date dateTime,String accountNo, double amount);
}
