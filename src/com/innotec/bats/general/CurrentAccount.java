package com.innotec.bats.general;

/**
 * Created by phoenix on 7/18/16.
 */
public class CurrentAccount extends Account
{
	public static final double DEF_MIN_BALANCE = 100.0;
	public static final double DEF_MAX_WITHDRAWAL_PER_DAY = 1000.0;
	public static final double DEF_MAX_TRANSFER_PER_DAY = 1000.0;
	private double minBalance;

	public CurrentAccount(String accountNo, double balance, boolean active, double maxWithdrawalPerDay,
			double maxTransferPerDay)
	{
		super(accountNo, balance, active, maxWithdrawalPerDay,
				maxTransferPerDay);
		//this.minBalance = minBalance;
	}

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public String toString() {return super.toString();}
}
