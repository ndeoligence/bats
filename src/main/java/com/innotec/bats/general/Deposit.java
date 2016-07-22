package com.innotec.bats.general;

/**
 * Created by phoenix on 7/20/16.
 */
public class Deposit extends Transaction {
    public static final double MIN_BALANCE = 100;
    public Deposit(String accountNo, double amount) {
        super(accountNo,amount);
    }
    public String toString() {
        return "Deposit of R" + getAmount() + " to: " + getPrimAccountNo();
    }
}
