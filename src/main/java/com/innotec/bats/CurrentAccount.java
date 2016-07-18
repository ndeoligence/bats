package com.innotec.bats;

/**
 * Created by phoenix on 7/18/16.
 */
public class CurrentAccount extends BankAccount {
    private static final double DEF_MIN_BALANCE = 1000;
    public CurrentAccount(AccountHolder accountHolder, String accountNo, double balance, double minBalance, boolean active) {
        super(accountHolder, accountNo, balance, minBalance, active);
    }
}
