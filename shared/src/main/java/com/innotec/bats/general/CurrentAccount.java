package com.innotec.bats.general;

/**
 * Created by phoenix on 7/18/16.
 */
public class CurrentAccount extends Account {
    public static final double MIN_BALANCE = 100.0;

    public CurrentAccount() {
    }

    public CurrentAccount(String accountNo, double balance, boolean active, double maxWithdrawalPerDay, double maxTransferPerDay, String accountHolderId) {
        super(accountNo, balance, active, maxWithdrawalPerDay, maxTransferPerDay, accountHolderId);

    }

    public CurrentAccount(String accountNo, double balance, boolean active, String accountHolderId) {
        super(accountNo, balance, active, accountHolderId);
    }

    public double getMinBalance() {
        return MIN_BALANCE;
    }

    @Override
    public String toString() {
        return "CurrentAccount{}";
    }
}
