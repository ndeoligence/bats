package com.innotec.bats.general;

public class Account 
{
	private String accountNo;
	private double balance;
	private boolean active;
	private double maxWithdrawalPerDay;
	private double maxTransferPerDay;
	private int startDate;
	private String accountHolderId;
	private final double defaultMaxWithdrawalPerDay = 1000.00;
	private final double defaultMaxTransferPerDay = 1000.00;
	
	public Account(String accountNo, double balance, boolean active, double maxWithdrawalPerDay, double maxTransferPerDay,String accountHolderId)
	{
		this.accountNo = accountNo;
		this.balance = balance;
		this.active = active;
		this.maxWithdrawalPerDay = maxWithdrawalPerDay;
		this.maxTransferPerDay = maxTransferPerDay;
		this.setAccountHolderId(accountHolderId);
	}
	
	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", balance=" + balance
				+ ", active=" + active + ", maxWithdrawalPerDay="
				+ maxWithdrawalPerDay + ", maxTransferPerDay="
				+ maxTransferPerDay + ",\nstartDate=" + startDate
				+ ", accountHolderId=" + accountHolderId
				+ ", defaultMaxWithdrawalPerDay=" + defaultMaxWithdrawalPerDay
				+ ", defaultMaxTransferPerDay=" + defaultMaxTransferPerDay
				+ "]";
	}

	public Account(String accountNo, double balance, boolean active)
	{
		this.accountNo = accountNo;
		this.balance = balance;
		this.active = active;
		this.maxWithdrawalPerDay = defaultMaxWithdrawalPerDay;
		this.maxTransferPerDay = defaultMaxTransferPerDay;
	}
	
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean isActive) {
		this.active = isActive;
	}

	public double getMaxWithdrawalPerDay() {
		return maxWithdrawalPerDay;
	}

	public void setMaxWithdrawalPerDay(double maxWithdrawalPerDay) {
		this.maxWithdrawalPerDay = maxWithdrawalPerDay;
	}

	public double getMaxTransferPerDay() {
		return maxTransferPerDay;
	}

	public void setMaxTransferPerDay(double maxTransferPerDay) {
		this.maxTransferPerDay = maxTransferPerDay;
	}

	public String getAccountHolderId() {
		return accountHolderId;
	}

	public void setAccountHolderId(String accountHolderId) {
		this.accountHolderId = accountHolderId;
	}
}
