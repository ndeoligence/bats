package com.innotec.bats.general;

public class AccountCreation extends TellerAction
{
	private Account newAccount;
	
	public AccountCreation (String tellerID, Account newAccount)
	{
		super (tellerID);
		this.newAccount = newAccount;	
	}

	public Account getNewAccount ()
	{
		return newAccount;
	}

	public void setNewAccount (Account newAccount)
	{
		this.newAccount = newAccount;
	}
	
	
}
