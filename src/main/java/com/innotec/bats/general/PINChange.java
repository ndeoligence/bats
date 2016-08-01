package com.innotec.bats.general;

public class PINChange extends AccountAction
{
	private String cardNo;
	private String newPIN;
	
	public PINChange(String cardNo, String newPIN)
	{
		super(null);
		this.cardNo = cardNo;
		this.newPIN = newPIN;
	}

	public String getCardNo ()
	{
		return cardNo;
	}

	public void setCardNo (String cardNo)
	{
		this.cardNo = cardNo;
	}

	public String getNewPIN ()
	{
		return newPIN;
	}

	public void setNewPIN (String newPIN)
	{
		this.newPIN = newPIN;
	}
}
