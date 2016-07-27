package com.innotec.bats.server.dao;

import java.util.ArrayList;

import com.innotec.bats.general.*;

public interface DAO_Interface
{
	public void addAccountHolder(AccountHolder newHolder);
	public void addAccountHolderCard(AccountHolderCard newCard);
	public void addCurrentAccount(CurrentAccount newCurrentAccount);
	public void addEmployee(Employee newEmployee);
	public void addAdmin(AdminCard newAdmin);
	public void addTransaction(Transaction newTransaction);
//	public void addATM(ATM newATM);
	public void addSavingsAccount(SavingsAccount newSavingsAccount);
	public void addCreditCardAccount(CreditCardAccount newCreditCardAccount);
	public AccountHolder getAccountHolder(String cardNo);
	public ArrayList<CreditCardAccount> getCreditCardAccounts(String cardNo);
	public Card getCard(String cardNo);
	public Employee getEmployee(String employeeID);
	public AdminCard getAdmin(String adminID);
	public Transaction getTransactionForAccount(String accountID);
//	public ATM getATM(int atmID);
	public boolean updateCardActivity(boolean cardActivity, String cardNo);
	public boolean changePIN(String newPIN, String cardNo);
	public boolean updateAccountActivity(boolean activity, String accountNo);
	public ArrayList<CurrentAccount> getCurrentAccount(String cardNo);
	public ArrayList<SavingsAccount> getSavingsAccount(String cardNo);
	
}
