package com.innotec.bats.general;

public class Transfer extends Transaction
{
	private String SecondaryAccountNumber;
    private final double MIN_AMOUNT = 100.00;
    private double transferAmount = 0.00;
    
    public Transfer(String secondaryAccountNumber,double transferAmount, String primaryAccountNo)
    {
    	super(primaryAccountNo, transferAmount);
    	this.SecondaryAccountNumber = secondaryAccountNumber;
    	this.transferAmount = transferAmount;
    	this.transferIsNotLessThanMinAmount();
    }
	
    public String getSecondaryAccountNumber()
	{
		return SecondaryAccountNumber;
	}
	public void setSecondaryAccountNumber(String secondaryAccountNumber)
	{
		SecondaryAccountNumber = secondaryAccountNumber;
	}
    
    public boolean transferIsNotLessThanMinAmount()
    {
    	if(MIN_AMOUNT > transferAmount)
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    }

	public String toString()
	{
		return "Transfer [SecondaryAccountNumber=" + SecondaryAccountNumber
				+ ", MIN_AMOUNT=" + MIN_AMOUNT + ", transferAmount="
				+ transferAmount + "]";
	}
       
}
