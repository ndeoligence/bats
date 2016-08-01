package com.innotec.bats.general;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Statement
{
	private ArrayList<Transaction> transactions;
	private Date dateStamp;
	private Time timeStamp;
	
	public Statement ()
	{
		transactions = new ArrayList<Transaction>();
	}
	
	public boolean addTransaction(Transaction transaction)
	{
		return transactions.add(transaction);
	}

	public ArrayList<Transaction> getTransactions ()
	{
		return transactions;
	}

	public void setTransactions (ArrayList<Transaction> transactions)
	{
		this.transactions = transactions;
	}
	
	
}
