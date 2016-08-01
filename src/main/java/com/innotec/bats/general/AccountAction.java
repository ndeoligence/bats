package com.innotec.bats.general;

public class AccountAction extends Action
{
	private String accountNo;
	
	public AccountAction (String accountNo)
	{
		this.accountNo = accountNo;
	}

	public String getAccountNo ()
	{
		return accountNo;
	}

	public void setAccountNo (String accountNo)
	{
		this.accountNo = accountNo;
	}
	
	public String toString ()
	{
		return "AccountAction [accountNo= " + super.toString() + accountNo + "]";
	}
}
