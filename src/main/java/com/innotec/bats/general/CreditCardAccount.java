package com.innotec.bats.general;


public class CreditCardAccount extends Account 
{
	
    public CreditCardAccount (String accountNo, double balance, boolean active, double maxWithdrawalPerDay, double maxTransferPerDay) 
    {
    	super(accountNo, balance, active, maxWithdrawalPerDay, maxTransferPerDay);
    }
    
 
}
