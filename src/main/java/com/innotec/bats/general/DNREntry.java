package com.innotec.bats.general;
import java.sql.Date;

public class DNREntry 
{
	private Date date;
	private int dateStamp;
	private long timeStamp;
	private Account acc;
	private String accNo;
	private double amount;
	
	public DNREntry(double amount)
	{
		this.dateStamp = date.getDate();
		this.timeStamp = date.getTime();
		this.accNo = acc.getAccountNo();
		this.amount = amount;
	}

	public int getDateStamp() {
		return dateStamp;
	}

	public void setDateStamp(int dateStamp) {
		this.dateStamp = dateStamp;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
