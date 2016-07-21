package com.innotec.bats.general;

/**
 * Created by phoenix on 7/18/16.
 */
public class CurrentAccount extends Account {
    public static final double MIN_BALANCE = 0;
    public CurrentAccount(AccountHolder accountHolder, String accountNo, double balance, double minBalance, boolean active) {
        super(accountHolder, accountNo, balance, minBalance, active);
    }
    public String toString() {return super.toString();}
}
