package com.innotec.bats.general;

public class Account
{
	private String accountNo;
	private double balance;
	private boolean active;
	private double maxWithdrawalPerDay;
	private double maxTransferPerDay;
	private int startDate;
	public final double DEFAULT_MAX_WITHDRAWAL_PER_DAY = 1000.00;
	public final double DEFAULT_MAX_TRANSFER_PER_DAY = 1000.00;

	public Account (String accountNo, double balance, boolean active,
			double maxWithdrawalPerDay, double maxTransferPerDay)
	{
		this.accountNo = accountNo;
		this.balance = balance;
		this.active = active;
		this.maxWithdrawalPerDay = maxWithdrawalPerDay;
		this.maxTransferPerDay = maxTransferPerDay;
	}

	public Account (String accountNo, double balance, boolean active)
	{
		this.accountNo = accountNo;
		this.balance = balance;
		this.active = active;
		this.maxWithdrawalPerDay = DEFAULT_MAX_WITHDRAWAL_PER_DAY;
		this.maxTransferPerDay = DEFAULT_MAX_TRANSFER_PER_DAY;
	}

	public String getAccountNo ()
	{
		return accountNo;
	}

	public void setAccountNo (String accountNo)
	{
		this.accountNo = accountNo;
	}

	public double getBalance ()
	{
		return balance;
	}

	public void setBalance (double balance)
	{
		this.balance = balance;
	}

	public boolean isActive ()
	{
		return active;
	}

	public void setActive (boolean isActive)
	{
		this.active = isActive;
	}

	public double getMaxWithdrawalPerDay ()
	{
		return maxWithdrawalPerDay;
	}

	public void setMaxWithdrawalPerDay (double maxWithdrawalPerDay)
	{
		this.maxWithdrawalPerDay = maxWithdrawalPerDay;
	}

	public double getMaxTransferPerDay ()
	{
		return maxTransferPerDay;
	}

	public void setMaxTransferPerDay (double maxTransferPerDay)
	{
		this.maxTransferPerDay = maxTransferPerDay;
	}
}