package com.innotec.bats.server.dao;

import java.util.ArrayList;

import com.innotec.bats.general.*;

public interface DAO_Interface
{
	void addAccountHolder(AccountHolder newHolder);
	void addAccountHolderCard(AccountHolderCard newCard);
	void addCurrentAccount(CurrentAccount newCurrentAccount);
	void addEmployee(Employee newEmployee);
	void addAdmin(AdminCard newAdmin);
	void addTransaction(Transaction newTransaction);
//	public void addATM(ATM newATM);
	void addSavingsAccount(SavingsAccount newSavingsAccount);
	void addCreditCardAccount(CreditCardAccount newCreditCardAccount);
	AccountHolder getAccountHolder(String cardNo);
	ArrayList<CreditCardAccount> getCreditCardAccounts(String cardNo);
	Card getCard(String cardNo);
	Employee getEmployee(String employeeID);
	AdminCard getAdmin(String adminID);
	Transaction getTransactionForAccount(String accountID);
//	public ATM getATM(int atmID);
	boolean updateCardActivity(boolean cardActivity, String cardNo);
	boolean changePIN(String newPIN, String cardNo);
	boolean updateAccountActivity(boolean activity, String accountNo);
	ArrayList<CurrentAccount> getCurrentAccount(String cardNo);
	ArrayList<SavingsAccount> getSavingsAccount(String cardNo);
	
}
