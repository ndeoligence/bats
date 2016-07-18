package com.innotec.bat;
import java.io.Serializable;

/**
 * Created by phoenix on 7/18/16.
 */
public class BankAccount implements Serializable {
    private AccountHolder accountHolder;
    private String accountNo;
    private double balance;
    private boolean active;
    public BankAccount(AccountHolder accountHolder, String accountNo, double balance, boolean active) {
        if (accountHolder==null || accountNo==null)
            throw new IllegalArgumentException("null BankAccount argument");
        this.accountHolder = accountHolder;
        this.accountNo = new String(accountNo);
        this.balance = balance;
        this.active = active;
    }
    public String toString() {
        return accountHolder.toString() + ": " + accountNo + "; " + balanceString();
    }
    private String balanceString() {
        StringBuilder sb = new StringBuilder("BankAccount::balanceString()");
        return sb.toString();
    }
}
