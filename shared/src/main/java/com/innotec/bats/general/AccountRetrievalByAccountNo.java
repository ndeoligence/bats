package com.innotec.bats.general;

import java.util.Objects;

/**
 * Created by phoenix on 7/27/16.
 */
public class AccountRetrievalByAccountNo extends AccountRetrieval
{
	private String accountNo;

	public AccountRetrievalByAccountNo() {
	}

	public AccountRetrievalByAccountNo (String accountNo, String tellerId)
	{
		super(tellerId);
		this.accountNo = accountNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AccountRetrievalByAccountNo)) return false;
		if (!super.equals(o)) return false;
		AccountRetrievalByAccountNo that = (AccountRetrievalByAccountNo) o;
		return Objects.equals(accountNo, that.accountNo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), accountNo);
	}

	@Override
	public String toString() {
		return "AccountRetrievalByAccountNo{" +
				"accountNo='" + accountNo + '\'' +
				'}';
	}
}
