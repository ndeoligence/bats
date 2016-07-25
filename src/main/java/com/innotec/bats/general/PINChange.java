package com.innotec.bats.general;

public class PINChange extends Action
{
	private String oldPIN;
	private String newPIN;

	public PINChange(String oldPIN, String newPIN)
	{
		this.oldPIN = oldPIN;
		this.newPIN = newPIN;
	}
}
