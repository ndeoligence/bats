package com.innotec.bats.general;

public class Transfer extends Transaction
{
	private String SecondaryAccountNo;
    private final double MIN_AMOUNT = 100.00;
    private double transferAmount = 0.00;
    
    public Transfer(String primaryAccountNo, String secondaryAccountNo, double transferAmount)
    {
    	super(primaryAccountNo, transferAmount);
    	this.SecondaryAccountNo = secondaryAccountNo;
    	this.transferAmount = transferAmount;
    	this.transferIsNotLessThanMinAmount();
    }
	
    public String getSecondaryAccountNumber()
	{
		return SecondaryAccountNo;
	}
    
	public void setSecondaryAccountNumber(String secondaryAccountNumber)
	{
		SecondaryAccountNo = secondaryAccountNumber;
	}
    
    public String getSecondaryAccountNo ()
	{
		return SecondaryAccountNo;
	}

	public void setSecondaryAccountNo (String secondaryAccountNo)
	{
		SecondaryAccountNo = secondaryAccountNo;
	}

	public double getTransferAmount ()
	{
		return transferAmount;
	}

	public void setTransferAmount (double transferAmount)
	{
		this.transferAmount = transferAmount;
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
		return "Transfer [SecondaryAccountNumber=" + SecondaryAccountNo
				+ ", MIN_AMOUNT=" + MIN_AMOUNT + ", transferAmount="
				+ transferAmount + "]";
	}
       
}
