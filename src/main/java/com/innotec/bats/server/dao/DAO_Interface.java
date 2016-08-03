package com.innotec.bats.server.dao;

import com.innotec.bats.general.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface DAO_Interface
{

	public boolean addAccountHolder(AccountHolder newHolder, String tellerId);	
	public boolean addCurrentAccount(CurrentAccount account, String tellerId);
	public boolean addSavingsAccount(SavingsAccount account, String tellerId);
	public boolean addAccountHolderCard(AccountHolderCard newCard);
	
	public boolean processWithdrawal(Withdrawal newWithdrawal);
	public boolean processDeposit(Deposit newDeposit);
	public boolean processTransfer(Transfer newTransfer);

	public boolean changePIN(String newPIN, String cardNo);
	
	public AdminCard getAdminCard (String cardNo);
	public AccountHolderCard getAccountHolderCard (String cardNo);
	
	public AccountHolder getAccountHolderByCardNo(String cardNo);
	public AccountHolder getAccountHolderByIdNo(String idNo);
	public AccountHolder getAccountHolderByAccountNo(String accNo);
	
	public boolean closeAccount(String accNo);
	public boolean deactivateCard(String cardNo);
	public boolean reactivateCard(String cardNo);
	
	public List<Transaction>getStatement(String accNo);
	
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
}
