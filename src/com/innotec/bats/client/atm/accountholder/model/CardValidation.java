package com.innotec.bats.client.atm.accountholder.model;

import com.innotec.bats.general.Card;

public class CardValidation
{
	private Card insertedCard, retrievedCard;
	
	public CardValidation (Card insertedCard, Card retrievedCard)
	{
			this.insertedCard = insertedCard;
			this.retrievedCard = retrievedCard;
	}
	
	public boolean validate ()
	{
			if ((insertedCard.getCardNo().equals(retrievedCard.getCardNo())) && (insertedCard.getPinNo().equals(retrievedCard.getPinNo())))
			{
				return true;
			}
			else
			{
				return false;
			}
	}
}
