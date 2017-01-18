package com.innotec.bats.general;

import java.util.Objects;

public class CardDeactivation extends Action {
    private String cardNo, atmID;

    public CardDeactivation() {
    }

    public CardDeactivation(String cardNo, String atmID) {
        this.cardNo = cardNo;
        this.atmID = atmID;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getAtmID() {
        return atmID;
    }

    public void setAtmID(String atmID) {
        this.atmID = atmID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardDeactivation)) return false;
        CardDeactivation that = (CardDeactivation) o;
        return Objects.equals(cardNo, that.cardNo) &&
                Objects.equals(atmID, that.atmID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNo, atmID);
    }

    @Override
    public String toString() {
        return "CardDeactivation{" +
                "cardNo='" + cardNo + '\'' +
                ", atmID='" + atmID + '\'' +
                '}';
    }
}
