package com.innotec.bats.general;

import java.util.Objects;

/**
 * Created by phoenix on 7/25/16.
 */
public class AccountHolderRetrievalByCardNo extends AccountHolderRetrieval {
    String cardNo;

    public AccountHolderRetrievalByCardNo() {
    }

    public AccountHolderRetrievalByCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountHolderRetrievalByCardNo)) return false;
        AccountHolderRetrievalByCardNo that = (AccountHolderRetrievalByCardNo) o;
        return Objects.equals(cardNo, that.cardNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNo);
    }

    @Override
    public String toString() {
        return "AccountHolderRetrievalByCardNo{" +
                "cardNo='" + cardNo + '\'' +
                '}';
    }
}
