package com.innotec.bats.general;

public class Transfer extends Transaction
{
	private String SecondaryAccountNo;
    private final double MIN_AMOUNT = 100.00;
    private double transferAmount = 0.00;
    
    public Transfer(String primaryAccountNo, String secondaryAccountNo, double transferAmount)
    {
    	super(primaryAccountNo, transferAmount);
		if (transferAmount < MIN_AMOUNT)
			throw new IllegalArgumentException("Transfer amount is lower than minimum allowable");
    	this.SecondaryAccountNo = secondaryAccountNo;
    	this.transferAmount = transferAmount;
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

	public String toString()
	{
		return "Transfer [SecondaryAccountNumber=" + SecondaryAccountNo
				+ ", MIN_AMOUNT=" + MIN_AMOUNT + ", transferAmount="
				+ transferAmount + "]";
	}
       
}
