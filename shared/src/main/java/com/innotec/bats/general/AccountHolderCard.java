package com.innotec.bats.general;

import java.util.Objects;

/**
 * Created by phoenix on 7/21/16.
 */
public class AccountHolderCard extends Card {
    public static final int CARD_NO_LEN = 16;

    String accountHolderIdNo;

    public AccountHolderCard() {
    }

    public AccountHolderCard(String cardNo, String pinNo, boolean active,
                             String accountHolderIdNo) {
        super(cardNo, pinNo, active);
        this.accountHolderIdNo = accountHolderIdNo;
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
        if (!(o instanceof AccountHolderCard)) return false;
        if (!super.equals(o)) return false;
        AccountHolderCard that = (AccountHolderCard) o;
        return Objects.equals(accountHolderIdNo, that.accountHolderIdNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accountHolderIdNo);
    }

    @Override
    public String toString() {
        return "AccountHolderCard{" +
                "accountHolderIdNo='" + accountHolderIdNo + '\'' +
                '}';
    }
}
