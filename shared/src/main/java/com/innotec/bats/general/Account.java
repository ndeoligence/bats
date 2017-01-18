package com.innotec.bats.general;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {
    private String accountNo;
    private double balance;
    private boolean active;
    private double maxWithdrawalPerDay;
    private double maxTransferPerDay;
    private int startDate;
    private String accountHolderIdNo;
    public final double DEFAULT_MAX_WITHDRAWAL_PER_DAY = 1000.00;
    public final double DEFAULT_MAX_TRANSFER_PER_DAY = 1000.00;
    public static final int ACCOUNT_NO_LEN = 11;

    public enum AccountType {
        CURRENT,
        SAVINGS,
        CREDIT
    }

    public Account() {
        this(null, 0, false, null);
    }

    public Account(String accountNo, double balance, boolean active,
                   double maxWithdrawalPerDay, double maxTransferPerDay,
                   String accountHolderIdNo) {
        this.accountNo = accountNo;
        this.balance = balance;
        this.active = active;
        this.maxWithdrawalPerDay = maxWithdrawalPerDay;
        this.maxTransferPerDay = maxTransferPerDay;
        this.accountHolderIdNo = accountHolderIdNo;
    }

    public Account(String accountNo, double balance, boolean active,
                   String accountHolderId) {
        this.accountNo = accountNo;
        this.balance = balance;
        this.active = active;
        this.accountHolderIdNo = accountHolderId;
        this.maxWithdrawalPerDay = DEFAULT_MAX_WITHDRAWAL_PER_DAY;
        this.maxTransferPerDay = DEFAULT_MAX_TRANSFER_PER_DAY;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getMaxWithdrawalPerDay() {
        return maxWithdrawalPerDay;
    }

    public void setMaxWithdrawalPerDay(double maxWithdrawalPerDay) {
        this.maxWithdrawalPerDay = maxWithdrawalPerDay;
    }

    public double getMaxTransferPerDay() {
        return maxTransferPerDay;
    }

    public void setMaxTransferPerDay(double maxTransferPerDay) {
        this.maxTransferPerDay = maxTransferPerDay;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public String getAccountHolderIdNo() {
        return accountHolderIdNo;
    }

    public void setAccountHolderIdNo(String accountHolderIdNo) {
        this.accountHolderIdNo = accountHolderIdNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 &&
                active == account.active &&
                Double.compare(account.maxWithdrawalPerDay, maxWithdrawalPerDay) == 0 &&
                Double.compare(account.maxTransferPerDay, maxTransferPerDay) == 0 &&
                startDate == account.startDate &&
                Objects.equals(accountNo, account.accountNo) &&
                Objects.equals(accountHolderIdNo, account.accountHolderIdNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNo, balance, active, maxWithdrawalPerDay, maxTransferPerDay, startDate, accountHolderIdNo);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNo='" + accountNo + '\'' +
                ", balance=" + balance +
                ", active=" + active +
                ", maxWithdrawalPerDay=" + maxWithdrawalPerDay +
                ", maxTransferPerDay=" + maxTransferPerDay +
                ", startDate=" + startDate +
                ", accountHolderIdNo='" + accountHolderIdNo + '\'' +
                '}';
    }
}
