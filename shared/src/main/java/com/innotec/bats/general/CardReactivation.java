package com.innotec.bats.general;

import java.util.Objects;

/**
 * Created by phoenix on 7/20/16.
 */
public class CardReactivation extends TellerAction {
    private String cardNo;

    public CardReactivation() {
    }

    public CardReactivation(String employeeNo, String cardNo) {
        super(employeeNo);
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
        if (!(o instanceof CardReactivation)) return false;
        if (!super.equals(o)) return false;
        CardReactivation that = (CardReactivation) o;
        return Objects.equals(cardNo, that.cardNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cardNo);
    }

    @Override
    public String toString() {
        return "CardReactivation{" +
                "cardNo='" + cardNo + '\'' +
                '}';
    }
}
