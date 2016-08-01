package com.innotec.bats.general;

/**
 * Created by phoenix on 7/27/16.
 */
public class AccountRetrievalByCardNo extends AccountRetrieval
{
	private String cardNo;

	public AccountRetrievalByCardNo (String cardNo, String tellerId)
	{
		super(tellerId);
		this.cardNo = cardNo;
	}

	public String getCardNo ()
	{
		return cardNo;
	}

	public void setCardNo (String cardNo)
	{
		this.cardNo = cardNo;
	}

	@Override
	public String toString ()
	{
		return "AccountRetrievalByCardNo{" + "cardNo='" + cardNo + '\'' + '}';
	}
}
