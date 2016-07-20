package com.innotec.bats.general;
import java.io.Serializable;

/**
 * Created by phoenix on 7/18/16.
 */
public class Account implements Serializable {
    private double minBalance;
    private AccountHolder accountHolder;
    private String accountNo;
    private double balance;
    private boolean active;
    public Account(AccountHolder accountHolder, String accountNo, double balance, double minBalance, boolean active) {
        if (accountHolder==null || accountNo==null)
            throw new IllegalArgumentException("null Account argument");
        this.accountHolder = accountHolder;
        this.accountNo = new String(accountNo);
        this.balance = balance;
        this.active = active;
    }
    public String toString() {
        return accountHolder.toString() + ": " + accountNo + "; R" + balance;
    }
    public AccountHolder getAccountHolder() {
        return accountHolder;
    }
    public String getAccountNo() {
        return accountNo;
    }
    public double getBalance() {
        return balance;
    }
    public double getMinBalance() {return minBalance;}
    public boolean isActive() {
        return active;
    }
    public boolean setBalance(double balance) {
        if (balance >= minBalance) {
            this.balance = balance;
            return true;
        } else {
            return false;
        }
    }
    public void setMinBalance(double minBalance) {this.minBalance=minBalance;}
    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean addAmount(double amount) {
        setBalance(getBalance() + amount);
        return true;
    }
    public boolean subtractAmount(double amount) {
        return setBalance(balance - amount);
    }
}
