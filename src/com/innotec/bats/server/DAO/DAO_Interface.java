package com.innotec.bats.server.DAO;

import java.util.List;
import com.innotec.bats.general.*;

public interface DAO_Interface
{
//	public void addAccountHolder(AccountHolder newHolder );
//	public void addAccountHolderCard(AccountHolderCard newCard);
//	public void addCurrentAccount(CurrentAccount newCurrentAccount);
//	public void addEmployee(Employee newEmployee);
//	public void addAdmin(AdminCard newAdmin);
//	public void addTransaction(Transaction newTransaction);
////	public void addATM(ATM newATM);
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
	public AccountHolder getAccountHolderByCardNo(String cardNo);			//3
	public AccountHolder getAccountHolderByidNo(String idNo);				//4
	/*get cards*/
	public AccountHolderCard getAccountHolderCardByCardNo(String cardNo);	//1
	public AccountHolderCard getAccountHolderCardByIdNo(String idNo);		
	public AdminCard getAdminCardById(String idNo);
	public AdminCard getAdminCardByCardNo(String cardNo);					//2
	/*get accounts*/
	public CreditCardAccount getCreditCardAccount(String accountHolderIdNo);
	public CurrentAccount getCurrentAccount(String accountHolderIdNo);
	public SavingsAccount getSavingsAccount(String accountHolderIdNo);
	public List<Account> getAccounts(String accountHolderIdNo);
	
}
