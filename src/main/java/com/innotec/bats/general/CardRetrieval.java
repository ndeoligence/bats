package com.innotec.bats.general;

public class CardRetrieval extends Action
{
	String cardNo;
	Card card;
	String accountNo;

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

	public Card getCard ()
	{
		return card;
	}

	public void setCard (Card card)
	{
		this.card = card;
	}

	@Override
	public String toString ()
	{
		return "CardRetrieval [cardNo=" + cardNo + "]";
	}
	
	
}
