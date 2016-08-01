package com.innotec.bats.general;

public class PINChange extends Action
{
	private String cardNo;
	private String newPIN;
	
	public PINChange(String cardNo, String newPIN)
	{
		this.cardNo = cardNo;
		this.newPIN = newPIN;
	}
	
}
