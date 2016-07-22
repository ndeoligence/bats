package com.innotec.bats.general;

public class PINChange extends Action
{
	private String oldPIN;
	private String newPIN1;
	private String newPIN2;
	
	public PINChange(String oldPIN, String newPIN1,String newPIN2)
	{
		this.oldPIN = oldPIN;
		this.newPIN1 = newPIN1;
		this.newPIN2 = newPIN2;
	}
	
	public boolean validateNewPIN()
	{
		if(newPIN1.equals(newPIN2))
		{
			return true;
		}
		return false;
	}
	
	public boolean validateOldPIN()
	{
		if(oldPIN.equals(Card.getPin()))
		{
			return true;
		}
		return false;
	}
	
	public void changePIN()
	{
		this.validateOldPIN();
		this.validateNewPIN();
		Card.setPin(newPIN1);
	}
}
