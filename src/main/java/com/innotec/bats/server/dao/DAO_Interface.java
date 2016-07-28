package com.innotec.bats.server.dao;

import java.util.List;
import com.innotec.bats.general.*;

public interface DAO_Interface
{
	/*get account holder*/
	AccountHolder getAccountHolderByCardNo(String cardNo);			//3
	AccountHolder getAccountHolderByidNo(String idNo);				//4

	/*get cards*/
	AccountHolderCard getAccountHolderCardByCardNo(String cardNo);	//1
	AccountHolderCard getAccountHolderCardByIdNo(String idNo);
	AdminCard getAdminCardById(String idNo);
	AdminCard getAdminCardByCardNo(String cardNo);					//2

	/*get accounts*/
	CreditCardAccount getCreditCardAccount(String accountHolderIdNo);
	CurrentAccount getCurrentAccount(String accountHolderIdNo);
	SavingsAccount getSavingsAccount(String accountHolderIdNo);
	List<Account> getAccounts(String accountHolderIdNo);
}
