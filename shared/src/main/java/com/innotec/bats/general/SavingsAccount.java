package com.innotec.bats.general;

import java.util.Date;

public class SavingsAccount extends Account {
    public static final double MIN_BALANCE = 1000.00;
    public static final int WITHDRAWAL_NOTICE_PERIOD = 32;
    public static final int MAX_WITHDRAWALS_PER_MONTH = 1;
    private Date fundsAvailableDate;
    private boolean withdrawalPending;
    private double pendingWithdrawalAmount;

    public SavingsAccount() {
    }

    public SavingsAccount(String accNo, double balance, boolean active, double maxWithdrawalAmountPerDay, double maxTransferralAmountPerDay, String accountHolderId) {
        super(accNo, balance, active, maxWithdrawalAmountPerDay, maxTransferralAmountPerDay, accountHolderId);
        withdrawalPending = false;
    }

    public SavingsAccount(String accNo, double balance, boolean active, String accountHolderId) {
        super(accNo, balance, active, accountHolderId);
        withdrawalPending = false;
    }

    public Date getFundsAvailableDate() {
        return fundsAvailableDate;
    }

    public void setFundsAvailableDate(Date fundsAvailableDate) {
        this.fundsAvailableDate = fundsAvailableDate;
    }

    public boolean isWithdrawalPending() { //if account holder wants to make a withdrawal notice, while another is still pending
        return withdrawalPending;
    }

    public void setWithdrawalPending(boolean withdrawalPending) {
        this.withdrawalPending = withdrawalPending;
    }

    public double getPendingWithdrawalAmount() {
        return pendingWithdrawalAmount;
    }

    public void setPendingWithdrawalAmount(double pendingWithdrawalAmount) {
        this.pendingWithdrawalAmount = pendingWithdrawalAmount;
    }
}
