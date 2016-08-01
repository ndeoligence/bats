package com.innotec.bats.general;

public class ViewStatement extends AccountAction
{
	private Statement statement;
	
	public ViewStatement(Statement statement, String accNo)
	{
		super(accNo);
		this.statement = statement;
	}

	public Statement getStatement()
	{
		return statement;
	}

	public void setStatement(Statement statement)
	{
		this.statement = statement;
	}
	
	
}
