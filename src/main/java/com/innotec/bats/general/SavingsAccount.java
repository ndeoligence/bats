package com.innotec.bats.general;

import java.util.Date;

public class SavingsAccount extends Account
{
	private final double MIN_BALANCE = 1000.00;
	private final int WITHDRAWAL_NOTICE_PERIOD = 32;
	private final int MAX_WITHDRAWALS_PER_MONTH = 1;
	private Date fundsAvailableDate;
	private boolean withdrawalPending;
	private double pendingWithdrawalAmount;
	private String accountHolderId;
	
	public SavingsAccount(String accNo, double balance, boolean active, double maxWithdrawalAmountPerDay, double maxTransferralAmountPerDay, String accountHolderId)
	{
		super(accNo, balance, active, maxWithdrawalAmountPerDay, maxTransferralAmountPerDay, accountHolderId);
		withdrawalPending = false;
	}
	
	public SavingsAccount(String accNo, double balance, boolean active, String accountHolderId)
	{
		super(accNo, balance, active, accountHolderId);
		withdrawalPending = false;
	}
	
	public Date getFundsAvailableDate()
	{
		return fundsAvailableDate;
	}

	public void setFundsAvailableDate(Date fundsAvailableDate)
	{
		this.fundsAvailableDate = fundsAvailableDate;
	}

	public double getMIN_BALANCE()
	{
		return MIN_BALANCE;
	}

	public int getWITHDRAWAL_NOTICE_PERIOD()
	{
		return WITHDRAWAL_NOTICE_PERIOD;
	}

	public int getMAX_WITHDRAWALS_PER_MONTH()
	{
		return MAX_WITHDRAWALS_PER_MONTH;
	}

	public boolean getWithdrawalPending()//if account holder wants to make a withdrawal notice, while another is still pending
	{
		return withdrawalPending;
	}
	
	public void setWithdrawalPending(boolean withdrawalPending)
	{
		this.withdrawalPending = withdrawalPending;
	}
	
	public double getPendingWithdrawalAmount ()
	{
		return pendingWithdrawalAmount;
	}

	public void setPendingWithdrawalAmount (double pendingWithdrawalAmount)
	{
		this.pendingWithdrawalAmount = pendingWithdrawalAmount;
	}

	public String toString()
	{
		return "SavingsAccount [MIN_BALANCE=" + MIN_BALANCE
				+ ", WITHDRAWAL_NOTICE_PERIOD=" + WITHDRAWAL_NOTICE_PERIOD
				+ ", MAX_WITHDRAWALS_PER_MONTH=" + MAX_WITHDRAWALS_PER_MONTH
				+ ", dateFromStartOfNoticePeriod="
				+ fundsAvailableDate + "]";
	}
	
	
}
