package com.innotec.bats.server.dao;

import java.util.List;
import com.innotec.bats.general.*;

public interface DAO_Interface
{
	public void addAccountHolder(AccountHolder newHolder);
	public void addCard(Card newCard);
	public void addAccount(Account newAccount);
	public void addEmployee(Employee newEmployee);
	public void addAdmin(Admin newAdmin);
	public void addTransaction(Transaction newTransaction);
	public void addATM(ATM newATM);
	public AccountHolder getAccountHolder(String cardNo);
	public List<Account> getAccount(String cardNo);
	public Card getCard(String cardNo);
	public Employee getEmployee(String employeeID);
	public Admin getAdmin(String adminID);
	public Transaction getTransactionForAccount(String accountID);
	public ATM getATM(int atmID);
	public boolean updateCardActivity(boolean cardActivity, String cardNo);
	public boolean changePIN(String newPIN, String cardNo);
	public boolean updateAccountActivity(boolean activity, String accountNo);
	public Transaction getTransactionForATM(int atmID);
}
