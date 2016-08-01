package com.innotec.bats.general;

/**
 * Created by phoenix on 7/20/16.
 */
public class CardReactivation extends TellerAction
{
	private String cardNo;

	public CardReactivation(String cardNo, String employeeNo)
	{
		super(employeeNo);
		this.cardNo = cardNo;
	}

	public String getCardNo()
	{
		return cardNo;
	}

	public String toString()
	{
		return "Card Reactivation request for card: " + cardNo;
	}
}
