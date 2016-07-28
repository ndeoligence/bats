package com.innotec.bats.general;

import java.util.Date;

public class SavingsAccount extends Account
{
	private final double MIN_BALANCE = 1000.00;
	private final int WITHDRAWAL_NOTICE_PERIOD = 32;
	private final int MAX_WITHDRAWALS_PER_MONTH = 1;
	private Date dateFromStartOfNoticePeriod;
	private boolean withdrawalPending;
	
	public SavingsAccount(String accNo, double balance, boolean active, double maxWithdrawalAmountPerDay, double maxTransferralAmountPerDay)
	{
		super(accNo, balance, active, maxWithdrawalAmountPerDay, maxTransferralAmountPerDay);
		withdrawalPending = false;
	}
	
	public SavingsAccount(String accNo, double balance, boolean active)
	{
		super(accNo, balance, active);
		withdrawalPending = false;
	}
	
	public Date getDateFromStartOfNoticePeriod()
	{
		return dateFromStartOfNoticePeriod;
	}

	public void setDateFromStartOfNoticePeriod(Date dateFromStartOfNoticePeriod)
	{
		this.dateFromStartOfNoticePeriod = dateFromStartOfNoticePeriod;
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
	
	public String toString()
	{
		return "SavingsAccount [MIN_BALANCE=" + MIN_BALANCE
				+ ", WITHDRAWAL_NOTICE_PERIOD=" + WITHDRAWAL_NOTICE_PERIOD
				+ ", MAX_WITHDRAWALS_PER_MONTH=" + MAX_WITHDRAWALS_PER_MONTH
				+ ", dateFromStartOfNoticePeriod="
				+ dateFromStartOfNoticePeriod + "]";
	}
	
	
}
