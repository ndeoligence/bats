package com.innotec.bats.general;

public class AccountCreation extends TellerAction
{
	private Account newAccount;
	private String accountHolderId;
	
	public AccountCreation (String tellerID, Account newAccount, String accountHolderId)
	{
		super (tellerID);
		this.accountHolderId = accountHolderId;
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
