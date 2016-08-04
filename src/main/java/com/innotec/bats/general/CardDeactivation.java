package com.innotec.bats.general;

public class CardDeactivation extends Action
{
	private String cardNo, atmID;
	
	public CardDeactivation (String cardNo, String atmID)
	{
		this.cardNo = cardNo;
		this.atmID = atmID;
	}

	public String getAtmID ()
	{
		return atmID;
	}

	public void setAtmID (String atmID)
	{
		this.atmID = atmID;
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
