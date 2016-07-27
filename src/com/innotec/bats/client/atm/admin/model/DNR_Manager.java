package com.innotec.bats.client.atm.admin.model;

import com.innotec.bats.client.atm.control.ATM_ServerComm;
import com.innotec.bats.general.Deposit;
import com.innotec.bats.general.Transaction;
import com.innotec.bats.general.Withdrawal;

public class DNR_Manager
{
	private double dispenserBalance = 190000.00;
	private Transaction transaction;
	private Withdrawal withdrawal;
	private Deposit deposit;
	private DNREntry dnrEntry;
	private DNRecord dnRecord;
	private DNR_DAO dnr_DAO;
	
	public DNR_Manager(Transaction transaction)
	{
		this.transaction = transaction;
		
		if(transaction instanceof Withdrawal)
		{
			dispenserBalance = dispenserBalance - transaction.getAmount();
			dnrEntry = new DNREntry(withdrawal.getAmount(), withdrawal.getPrimAccountNo());
			dnRecord.add(dnrEntry);
			dnr_DAO.writeToDNR(dnRecord);
		}
		if(transaction instanceof Deposit)
		{
			dispenserBalance = dispenserBalance + transaction.getAmount();
			dnrEntry = new DNREntry(deposit.getAmount(), deposit.getPrimAccountNo());
			dnRecord.add(dnrEntry);
			dnr_DAO.writeToDNR(dnRecord);
		}
	}
	
	public void resetDispenserBalance()
	{
		dispenserBalance = 190000.00;
	}
	
	public Object printDNR()
	{
		Object dnr = dnr_DAO.readFromDNR();
		this.createNextDateDNR();
		this.resetDispenserBalance();
		return dnr;
	}
	
	public void createNextDateDNR()
	{
		dnr_DAO = new DNR_DAO();
		dnRecord = new DNRecord(ATM_ServerComm.getATM_ID());
		
	}
}
