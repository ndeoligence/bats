package com.innotec.bats.general;

import java.util.ArrayList;

public class StatementRetrieval extends AccountAction
{
	private ArrayList<Transaction> statement;

	
	public StatementRetrieval (String accountNo)
	{
		super(accountNo);
	}

	public ArrayList<Transaction> getStatement ()
	{
		return statement;
	}

	public void setStatement (ArrayList<Transaction> statement)
	{
		this.statement = statement;
	}
	
	public boolean addTransactionToStatement(Transaction transaction)
	{
		statement.add(transaction);
		return true;
	}
	
	
	@Override
	public String toString ()
	{
		return "StatementRetrieval [statement=" + statement + super.toString() + "]";
	}

}
