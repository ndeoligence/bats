package com.innotec.bats.general;

public class AccountRetrieval extends TellerAction
{
	private Account account;
	private String idNumber;
	
	public AccountRetrieval(Account account, String idNumber, String tellerID)
	{
		super(tellerID);
		this.account = account;
		this.idNumber = idNumber;
	}
	public Account getAccount()
	{
		return account;
	}
	public void setAccount(Account account)
	{
		this.account = account;
	}
	public String getIdNumber()
	{
		return idNumber;
	}
	
	public String toString()
	{
		return "AccountRetrieval [idNumber=" + idNumber + "]";
	}
	
}
