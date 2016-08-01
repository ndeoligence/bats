package com.innotec.bats.general;

import java.util.Arrays;
import java.util.Date;

public class DNREntry
{
	private Date date;
	private String accountNo;
	private double amount;
	private int[] dispensedNotes;

	public DNREntry (double amount, String accountNo, int[] dispensedNotes)
	{
		date = new Date();
		this.amount = amount;
		this.accountNo = accountNo;
		this.dispensedNotes = dispensedNotes;
	}

	public Date getDateStamp ()
	{
		return date;
	}


	public String getAccountNo ()
	{
		return accountNo;
	}

	public void setAccountNo (String accNo)
	{
		this.accountNo = accNo;
	}

	public double getAmount ()
	{
		return amount;
	}

	public void setAmount (double amount)
	{
		this.amount = amount;
	}

	public int[] getDispensedNotes ()
	{
		return dispensedNotes;
	}

	public void setDispensedNotes (int[] dispensedNotes)
	{
		this.dispensedNotes = dispensedNotes;
	}

	@Override
	public String toString ()
	{
		return "DNREntry [date=" + date + ", accountNo=" + accountNo
				+ ", amount=" + amount + ", dispensedNotes="
				+ Arrays.toString(dispensedNotes) + "]";
	}
	

}
