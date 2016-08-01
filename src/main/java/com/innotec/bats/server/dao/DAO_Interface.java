package com.innotec.bats.server.dao;

import java.sql.SQLException;
import java.util.List;

import com.innotec.bats.general.*;

public interface DAO_Interface
{
	public void addEmployee(Employee newEmployee);
	public void addAdminCard(AdminCard newAdmin);
	public void addAccountHolder(AccountHolder newHolder, String tellerId);	
	public void addAccountHolderCard(AccountHolderCard newCard);
	public void addCurrentAccount(String accountHolderId, CurrentAccount account);
	public void addSavingsAccount(String accountHolderId, SavingsAccount account);
	public void addCreditCardAccount(String accountHolderId, CreditCardAccount account);
	public void addTransaction(Transaction newTransaction);

//	public boolean updateCardActivity(boolean cardActivity, String cardNo);
//	public boolean changePIN(String newPIN, String cardNo);
//	public boolean updateAccountActivity(boolean activity, String accountNo);
	
//	public AccountHolder getAccountHolderByCardNo(String cardNo) throws SQLException;
//	public AccountHolderCard getAccountHolderCardByIdNo(String idNo);		
//	public AdminCard getAdminCardById(String idNo);
	public Employee getEmployee(String employeeID);
	public Transaction getTransactionForAccount(String accountID);
	public AdminCard getAdminCardByCardNo(String cardNo);
	public AccountHolder getAccountHolderByidNo(String idNo);
	public AccountHolderCard getAccountHolderCardByCardNo(String cardNo);
	public CreditCardAccount getCreditCardAccount(String accountHolderIdNo);
	public CurrentAccount getCurrentAccount(String accountHolderIdNo);
	public SavingsAccount getSavingsAccount(String accountHolderIdNo);
	public List<Account> getAccounts(String accountHolderIdNo);
}
