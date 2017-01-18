package com.innotec.bats.general;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by phoenix on 7/18/16.
 */
public class Card implements Serializable {
    private String cardNo;
    private String pinNo;
    private boolean active;

    public Card() {
    }

    public Card(String cardNo, String pinNo, boolean active) {
        this.cardNo = cardNo;
        this.pinNo = pinNo;
        this.active = active;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPinNo() {
        return pinNo;
    }

    public void setPinNo(String pinNo) {
        this.pinNo = pinNo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return active == card.active &&
                Objects.equals(cardNo, card.cardNo) &&
                Objects.equals(pinNo, card.pinNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNo, pinNo, active);
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNo='" + cardNo + '\'' +
                ", pinNo='" + pinNo + '\'' +
                ", active=" + active +
                '}';
    }
}
