package com.innotec.bats.general;

public class CardDeactivation
{
	private String cardNo;
	
	public CardDeactivation (String cardNo)
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
		return "CardDeactivation [cardNo=" + cardNo + "]";
	}
	
	
}
