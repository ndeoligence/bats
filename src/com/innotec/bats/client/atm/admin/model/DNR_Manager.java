package com.innotec.bats.client.atm.admin.model;

import com.innotec.bats.client.atm.accountholder.control.ATMApplication;
import com.innotec.bats.client.atm.control.ATM_ServerComm;
import com.innotec.bats.client.teller.control.BankTellerApplication;
import com.innotec.bats.general.Deposit;
import com.innotec.bats.general.Transaction;
import com.innotec.bats.general.Withdrawal;

public class DNR_Manager
{
	//private double dispenserBalance = 190000.00;
	private Transaction transaction;
	private Dispenser dispenser;
	private Withdrawal withdrawal;
	private Deposit deposit;
	private DNREntry dnrEntry;
	private DNRecord dnRecord;
	private DNR_DAO dnr_DAO;
	private int[] record;
	
	public DNR_Manager()
	{
		dnr_DAO = new DNR_DAO();
		dnRecord = new DNRecord(ATMApplication.serverComm.getATM_ID());
		dispenser = new Dispenser();
	}
	
	public DNR_Manager(Transaction transaction)
	{
		this.transaction = transaction;
		
		if(transaction instanceof Withdrawal)
		{
			//dispenserBalance = dispenserBalance - transaction.getAmount();
			record = dispenser.dispense(withdrawal.getAmount());
			dnrEntry = new DNREntry(record, withdrawal.getPrimAccountNo());
			//dnRecord.readFromDAOfile();
			dnRecord.add(dnrEntry);
			dnr_DAO.writeToDNR(dnRecord);
		}
		if(transaction instanceof Deposit)
		{
			//dispenserBalance = dispenserBalance + transaction.getAmount();
			//dnrEntry = new DNREntry(deposit.getAmount(), deposit.getPrimAccountNo());
			dnRecord.readFromDAOfile();
			dnRecord.add(dnrEntry);
			dnr_DAO.writeToDNR(dnRecord);
		}
	}
	
//	public void resetDispenserBalance()
//	{
//		dispenserBalance = 190000.00;
//	}
	
	public Object printDNR()
	{
		Object dnr = dnr_DAO.readFromDNR();
//		this.createNextDateDNR();
//		this.resetDispenserBalance();
		return dnr;
	}
	
//	public void createNextDateDNR()
//	{
//		dnr_DAO = new DNR_DAO();
//		dnRecord = new DNRecord(ATMApplication.serverComm.getATM_ID());
//		dispenser = new Dispenser();	
//	}
}
