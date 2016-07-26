package com.innotec.bats.general;

public class Withdrawal 
{
	public static final double MIN_BALANCE = 10.00; // Why should this be here?
	private int waitingPeriod;
	
	public Withdrawal(int waitingPeriod)
	{
		this.waitingPeriod = waitingPeriod;
	}

	public int getWaitingPeriod() {
		return waitingPeriod;
	}
}
