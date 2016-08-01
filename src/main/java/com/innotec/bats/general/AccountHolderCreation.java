package com.innotec.bats.general;

public class AccountHolderCreation extends TellerAction
{
	private AccountHolder accountHolder;
	
	public AccountHolderCreation(AccountHolder accountHolder, String tellerID)
	{
		super(tellerID);
		this.accountHolder = accountHolder;
	}

	public AccountHolder getAccount()
	{
		return accountHolder;
	}

	public void setAccount(AccountHolder accountHolder)
	{
		this.accountHolder = accountHolder;
	}
	
}
