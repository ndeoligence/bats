package com.innotec.bats.general;

/**
 * Created by phoenix on 7/18/16.
 */
public class CurrentAccount extends Account {
    public static final double DEF_MIN_BALANCE = 0.0;
    public static final double DEF_MAX_WITHDRAWAL_PER_DAY = 1000.0;
    public static final double DEF_MAX_TRANSFER_PER_DAY = 1000.0;
    private double minBalance;
    public CurrentAccount(String accountNo, double balance, boolean active, double minBalance, double maxWithdrawalPerDay, double maxTransferPerDay) {
        super(accountNo, balance, active, maxWithdrawalPerDay, maxTransferPerDay);
        this.minBalance = minBalance;
    }
    public CurrentAccount(String accountNo, double balance, boolean active, double minBalance) {
        this(accountNo, balance, active, minBalance, DEF_MAX_WITHDRAWAL_PER_DAY, DEF_MAX_TRANSFER_PER_DAY);
    }
    public CurrentAccount(String accountNo, double balance, boolean active) {
        this(accountNo,balance,active,DEF_MIN_BALANCE);
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public String toString() {return super.toString();}
}
