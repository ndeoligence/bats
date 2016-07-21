package com.innotec.bats.general;

import java.util.ArrayList;

public class Statement
{
	private ArrayList<Transactions> transactions;
	
	public Statement ()
	{
		transactions = new ArrayList<Transactions>();
	}
	
	public boolean addTransaction(Transaction transaction)
	{
		transactions.add(transaction);
	}

	public ArrayList<Transactions> getTransactions ()
	{
		return transactions;
	}

	public void setTransactions (ArrayList<Transactions> transactions)
	{
		this.transactions = transactions;
	}
	
	
}
