package com.innotec.bats.general;

/**
 * Created by phoenix on 7/18/16.
 */
public class CreditAccount extends Account {
    public CreditAccount(AccountHolder accountHolder, String accountNo, double balance, double minBalance, boolean active) {
        super(accountHolder, accountNo, balance, minBalance, active);
    }
}
