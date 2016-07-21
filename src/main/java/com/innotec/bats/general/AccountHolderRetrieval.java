package com.innotec.bats.general;

public class AccountHolderRetrieval extends Action
{
	private String cardNo;
	private AccountHolder accountHolder;
	
	public AccountHolderRetrieval(String cardNo, AccountHolder accountHolder)
	{
		this.cardNo = cardNo;
		this.accountHolder = accountHolder;
	}
	public String getCardNo()
	{
		return cardNo;
	}
	public void setCardNo(String cardNo)
	{
		this.cardNo = cardNo;
	}
	public AccountHolder getAccountHolder()
	{
		return accountHolder;
	}
	public void setAccountHolder(AccountHolder accountHolder)
	{
		this.accountHolder = accountHolder;
	}
	
	public String toString()
	{
		return "AccountHolderRetrieval [cardNo=" + cardNo + "]";
	}
	
}
