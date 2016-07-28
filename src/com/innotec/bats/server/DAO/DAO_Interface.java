package com.innotec.bats.server.DAO;

import java.util.List;
import com.innotec.bats.general.*;

public interface DAO_Interface
{
	
	
	public void addCurrentAccount(CurrentAccount newCurrentAccount);
	public void addEmployee(Employee newEmployee);
	public void addAdmin(AdminCard newAdmin);
	public void addTransaction(Transaction newTransaction);
////	public void addATM(ATM newATM);
	public void addSavingsAccount(SavingsAccount newSavingsAccount);
	public void addCreditCardAccount(CreditCardAccount newCreditCardAccount);
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
	public AccountHolder getAccountHolderByCardNo(String cardNo);
	public AccountHolder getAccountHolderByidNo(String idNo);
	/*get cards*/
	public AccountHolderCard getAccountHolderCardByCardNo(String cardNo);
	public AccountHolderCard getAccountHolderCardByIdNo(String idNo);		
	public AdminCard getAdminCardById(String idNo);
	public AdminCard getAdminCardByCardNo(String cardNo);
	/*get accounts*/
	public CreditCardAccount getCreditCardAccount(String accountHolderIdNo);
	public CurrentAccount getCurrentAccount(String accountHolderIdNo);
	public SavingsAccount getSavingsAccount(String accountHolderIdNo);
	public List<Account> getAccounts(String accountHolderIdNo);
	/*Adder methods*/
	//teller
	public AccountHolder addAccountHolder(AccountHolder newHolder, String tellerId);		//1	
	public AccountHolderCard addAccountHolderCard(AccountHolderCard newCard);
	public Account addCurrentAccount(String accountHolderId, CurrentAccount account);		//2
	public Account addSavingsAccount(String accountHolderId, SavingsAccount account);		//3
	public Account addCreditCardAccount(String accountHolderId, CreditCardAccount account);	//extra
	//server
	public Teller addTeller(Teller newTeller);												//4
	public ATMAdmin addATMAdmin(ATMAdmin newAdmin);											//5
	/*getter methods*/ // very low priority
	public ATMAdmin getATMAdmin(String atmAdminId);
	public Teller getTeller(String tellerId);
}
