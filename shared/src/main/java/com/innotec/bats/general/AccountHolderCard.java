package com.innotec.bats.general;

/**
 * Created by phoenix on 7/21/16.
 */
public class AccountHolderCard extends Card
{
	public static final int CARD_NO_LEN = 16;

	String accountHolderIdNo;

	public AccountHolderCard (String cardNo, String pinNo, boolean active,
			String accountHolderIdNo)
	{
		super(cardNo, pinNo, active);
		this.accountHolderIdNo = accountHolderIdNo;
	}

	public String getAccountHolderIdNo ()
	{
		return accountHolderIdNo;
	}

	public void setAccountHolderIdNo (String accountHolderIdNo)
	{
		this.accountHolderIdNo = accountHolderIdNo;
	}

	@Override
	public String toString ()
	{
		return "AccountHolderCard{" + "cardNo='" + getCardNo() + '\''
				+ "accountHolderIdNo='" + accountHolderIdNo + '\'' + '}';
	}
}
