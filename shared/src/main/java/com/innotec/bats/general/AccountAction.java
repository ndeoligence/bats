package com.innotec.bats.general;

import java.util.Objects;

public class AccountAction extends Action
{
	private String accountNo;

	public AccountAction() {
	}

	public AccountAction (String accountNo)
	{
		this.accountNo = accountNo;
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AccountAction)) return false;
		AccountAction that = (AccountAction) o;
		return Objects.equals(accountNo, that.accountNo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNo);
	}

	@Override
	public String toString() {
		return "AccountAction{" +
				"accountNo='" + accountNo + '\'' +
				'}';
	}
}
