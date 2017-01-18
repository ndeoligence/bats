package com.innotec.bats.general;

import java.util.Objects;

public class AccountCreation extends TellerAction
{
	private Account newAccount;
	private String accountHolderId;

	public AccountCreation() {
	}

	public AccountCreation (String tellerID, Account newAccount, String accountHolderId)
	{
		super (tellerID);
		this.accountHolderId = accountHolderId;
		this.newAccount = newAccount;	
	}


	public Account getNewAccount() {
		return newAccount;
	}

	public void setNewAccount(Account newAccount) {
		this.newAccount = newAccount;
	}

	public String getAccountHolderId() {
		return accountHolderId;
	}

	public void setAccountHolderId(String accountHolderId) {
		this.accountHolderId = accountHolderId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AccountCreation)) return false;
		if (!super.equals(o)) return false;
		AccountCreation that = (AccountCreation) o;
		return Objects.equals(newAccount, that.newAccount) &&
				Objects.equals(accountHolderId, that.accountHolderId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), newAccount, accountHolderId);
	}

	@Override
	public String toString() {
		return "AccountCreation{" +
				"newAccount=" + newAccount +
				", accountHolderId='" + accountHolderId + '\'' +
				'}';
	}
}
