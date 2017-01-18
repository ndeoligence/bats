package com.innotec.bats.general;

import java.util.ArrayList;
import java.util.Objects;

public class AccountHolder extends Person {
    private String address;
    private String contactNo;
    private Card card;
    private ArrayList<Account> accounts;

    public AccountHolder() {
    }

    public AccountHolder(String name, String surname, String idNo, String address, String contactNo) {
        super(name, surname, idNo);

        this.address = new String(address);
        this.contactNo = new String(contactNo);
        accounts = new ArrayList<>();
    }

    public AccountHolder(String name, String surname, String idNo, String address, String contactNo, Card card) {
        super(name, surname, idNo);
        this.card = card;
        this.address = new String(address);
        this.contactNo = new String(contactNo);
        accounts = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountHolder)) return false;
        if (!super.equals(o)) return false;
        AccountHolder that = (AccountHolder) o;
        return Objects.equals(address, that.address) &&
                Objects.equals(contactNo, that.contactNo) &&
                Objects.equals(card, that.card) &&
                Objects.equals(accounts, that.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, contactNo, card, accounts);
    }

    @Override
    public String toString() {
        return "AccountHolder{" +
                "address='" + address + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", card=" + card +
                ", accounts=" + accounts +
                '}';
    }
}
