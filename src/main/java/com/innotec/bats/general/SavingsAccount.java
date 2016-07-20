package com.innotec.bats.general;

/**
 * Created by phoenix on 7/18/16.
 */
public class SavingsAccount extends Account {
    private static final double DEF_MIN_BALANCE = 1000;
    public SavingsAccount(AccountHolder accountHolder, String accountNo, double balance, double minBalance, boolean active) {
        super(accountHolder, accountNo, balance, minBalance, active);
    }

}
