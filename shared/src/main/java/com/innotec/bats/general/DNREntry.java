package com.innotec.bats.general;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class DNREntry {
    private Date date;
    private String accountNo;
    private double amount;
    private int[] dispensedNotes;

    public DNREntry() {
    }

    public DNREntry(double amount, String accountNo, int[] dispensedNotes) {
        date = new Date();
        this.amount = amount;
        this.accountNo = accountNo;
        this.dispensedNotes = dispensedNotes;
    }

    public Date getDateStamp() {
        return date;
    }


    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accNo) {
        this.accountNo = accNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int[] getDispensedNotes() {
        return dispensedNotes;
    }

    public void setDispensedNotes(int[] dispensedNotes) {
        this.dispensedNotes = dispensedNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DNREntry)) return false;
        DNREntry dnrEntry = (DNREntry) o;
        return Double.compare(dnrEntry.amount, amount) == 0 &&
                Objects.equals(date, dnrEntry.date) &&
                Objects.equals(accountNo, dnrEntry.accountNo) &&
                Arrays.equals(dispensedNotes, dnrEntry.dispensedNotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, accountNo, amount, dispensedNotes);
    }

    @Override
    public String toString() {
        return "DNREntry [date=" + date + ", accountNo=" + accountNo
                + ", amount=" + amount + ", dispensedNotes="
                + Arrays.toString(dispensedNotes) + "]";
    }
}
