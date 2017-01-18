package com.innotec.bats.general;

/**
 * Created by phoenix on 7/27/16.
 */
public abstract class AccountRetrieval extends TellerAction
{
	public AccountRetrieval() {
	}

	public AccountRetrieval(String employeeNo) {
		super(employeeNo);
	}

	@Override
	public String toString() {
		return "AccountRetrieval{}";
	}
}
