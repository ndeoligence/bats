package com.innotec.bats.general;

/**
 * Created by phoenix on 7/27/16.
 */
public abstract class AccountRetrieval extends TellerAction
{
	public AccountRetrieval (String tellerID)
	{
		super(tellerID);
	}

	@Override
	public String toString ()
	{
		return "AccountRetrieval";
	}
}
