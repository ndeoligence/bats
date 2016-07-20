package com.innotec.bats.general;

/**
 * Created by phoenix on 7/20/16.
 */
public class Transaction extends ATMAction {
    String accountNo;
    double amount;
    public Transaction(String accountNo, double amount) {
        this.accountNo = new String(accountNo);
        this.amount = amount;
    }
    public String getAccountNo() {return accountNo;}
    public double getAmount() {return amount;}
}
