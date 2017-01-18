package com.innotec.bats.general;

import java.util.Objects;

public class CardRetrieval extends Action {
    String cardNo;

    public CardRetrieval() {
    }

    public CardRetrieval(String cardNo) {
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
        if (!(o instanceof CardRetrieval)) return false;
        CardRetrieval that = (CardRetrieval) o;
        return Objects.equals(cardNo, that.cardNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNo);
    }

    @Override
    public String toString() {
        return "CardRetrieval{" +
                "cardNo='" + cardNo + '\'' +
                '}';
    }
}
