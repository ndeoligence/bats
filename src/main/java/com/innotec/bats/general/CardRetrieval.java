package com.innotec.bats.general;

public class CardRetrieval extends Action
{
	String cardNo;

	public CardRetrieval (String cardNo)
	{
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
		return "CardRetrieval [idNo=" + cardNo + "]";
	}
	
	
}
