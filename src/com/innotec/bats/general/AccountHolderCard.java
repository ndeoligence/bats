package com.innotec.bats.general;

/**
 * Created by phoenix on 7/21/16.
 */
public class AccountHolderCard extends Card {
    public static final int CARD_NO_LEN = 16;
    public AccountHolderCard(String cardNo, String pinNo, boolean active) {
        super(cardNo, pinNo, active);
    }

    @Override
    public String toString() {
        return "AccountHolderCard{"+super.toString()+"}";
    }
}
