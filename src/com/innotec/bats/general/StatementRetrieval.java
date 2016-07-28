package com.innotec.bats.general;

import java.util.ArrayList;

public class StatementRetrieval extends Action
{
	private ArrayList<Transaction> statement;
	private String accountNo;
	
	public StatementRetrieval (String accountNo)
	{
		this.accountNo = accountNo;
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
	
	public String getAccountNo ()
	{
		return accountNo;
	}

	public void setAccountNo (String accountNo)
	{
		this.accountNo = accountNo;
	}
	
	@Override
	public String toString ()
	{
		return "StatementRetrieval [statement=" + statement + ", accountNo="
				+ accountNo + "]";
	}

}
