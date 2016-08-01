package com.innotec.bats.general;

public class Withdrawal extends Transaction
{
	public static final double MIN_AMOUNT = 10.0; 
	private boolean waitingPeriod;
	
	public Withdrawal (String accountNo, double amount, boolean waitingPeriod)
	{
		super (accountNo, amount,"Withdrawal");
		this.waitingPeriod = waitingPeriod;
	}
	
	public boolean getWaitingPeriodApplicable() 
	{
		return waitingPeriod;
	}
}
