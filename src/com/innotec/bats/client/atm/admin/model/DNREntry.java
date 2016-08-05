package com.innotec.bats.client.atm.admin.model;

import java.util.Date;

import com.innotec.bats.general.Account;

public class DNREntry
{
	private Date date;
	private String dateStamp;
	//private Account accNo;
	private String accNo;
	//private double amount;
	private int[] record;

	public DNREntry(int[] record, String accNo)
	{
		date = new Date();
		this.dateStamp = date.toString();
		this.accNo = accNo;
		this.record = record;
	}

	public String getDateStamp()
	{
		return dateStamp;
	}

	public void setDateStamp(String dateStamp)
	{
		this.dateStamp = date.toString();
	}

	public String getAccNo()
	{
		return accNo;
	}

	public void setAccNo(String accNo)
	{
		this.accNo = accNo;
	}

	public int[] getRecord()
	{
		return record;
	}

	public void setRecord(int[] record)
	{
		this.record = record;
	}

}
