package com.innotec.bats.server.dao;

import java.sql.SQLException;
import java.util.List;

import com.innotec.bats.general.*;

public interface DAO_Interface
{
	
	
//	public void addCurrentAccount(CurrentAccount newCurrentAccount);
//	public void addEmployee(Employee newEmployee);
//	public void addAdmin(AdminCard newAdmin) throws SQLException;
//	public void addTransaction(Transaction newTransaction);
//////	public void addATM(ATM newATM);
//	public void addSavingsAccount(SavingsAccount newSavingsAccount);
//	public void addCreditCardAccount(CreditCardAccount newCreditCardAccount);
//	
//	
//	public Employee getEmployee(String employeeID);
//	
//	
//	
//	public Transaction getTransactionForAccount(String accountID);
////	public ATM getATM(int atmID);
//	public boolean updateCardActivity(boolean cardActivity, String cardNo);
//	public boolean changePIN(String newPIN, String cardNo);
//	public boolean updateAccountActivity(boolean activity, String accountNo);
	
	/*get account holder*/
//	public AccountHolder getAccountHolderByCardNo(String cardNo) throws SQLException;
	public AccountHolder getAccountHolderByidNo(String idNo) throws SQLException;
	/*get cards*/
	public AccountHolderCard getAccountHolderCardByCardNo(String cardNo) throws SQLException;
//	public AccountHolderCard getAccountHolderCardByIdNo(String idNo) throws SQLException;		
//	public AdminCard getAdminCardById(String idNo) throws SQLException;
//	public AdminCard getAdminCardByCardNo(String cardNo);
	/*get accounts*/
//	public CreditCardAccount getCreditCardAccount(String accountHolderIdNo);
//	public CurrentAccount getCurrentAccount(String accountHolderIdNo);
//	public SavingsAccount getSavingsAccount(String accountHolderIdNo);
//	public List<Account> getAccounts(String accountHolderIdNo);
	/*Adder methods*/
	//teller
	public void addAccountHolder(AccountHolder newHolder, String tellerId);		//1	
	public void addAccountHolderCard(AccountHolderCard newCard);
//	public Account addCurrentAccount(String accountHolderId, CurrentAccount account);		//2
//	public Account addSavingsAccount(String accountHolderId, SavingsAccount account);		//3
//	public Account addCreditCardAccount(String accountHolderId, CreditCardAccount account);	//extra
//	//server
//	public Teller addTeller(Teller newTeller) throws SQLException;												//4
//	public ATMAdmin addATMAdmin(ATMAdmin newAdmin) throws SQLException;											//5
//	/*getter methods*/ // very low priority
//	public ATMAdmin getATMAdmin(String atmAdminId) throws SQLException;
//	public Teller getTeller(String tellerId);
}
