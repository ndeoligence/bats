package com.innotec.bats.general;

/**
 * Created by phoenix on 7/18/16.
 */
public class CurrentAccount extends Account {
    public static final double MIN_BALANCE = 100.0;
    public static final double MAX_WITHDRAWAL_PER_DAY = 1000.0;
    public static final double MAX_TRANSFER_PER_DAY = 1000.0;
    public CurrentAccount(String accountNo, double balance, boolean active) {
        super(accountNo, balance, active, MAX_WITHDRAWAL_PER_DAY, MAX_TRANSFER_PER_DAY);
    }
    @Override
    public String toString() {
        return "CurrentAccount{"+super.toString()+"}";
    }
}
