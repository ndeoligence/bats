package com.innotec.bats.general;

public class Account 
{
	private String accNo;
	private double balance;
	private boolean active;
	private double maxWithdrawalPerDay;
	private double maxTransferPerDay;
	private int startDate;
	
	public Account(String accNo, double balance, boolean active, double maxWithdrawalPerDay, double maxTransferPerDay)
	{
		this.accNo = accNo;
		this.balance = balance;
		this.active = active;
		this.maxWithdrawalPerDay = maxWithdrawalPerDay;
		this.maxTransferPerDay = maxTransferPerDay;
	}
	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
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
}
