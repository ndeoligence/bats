package com.innotec.bats.general;

/**
 * Created by phoenix on 7/25/16.
 */
public class AccountHolderRetrievalByCardNo extends AccountHolderRetrieval {
    String cardNo;
    public AccountHolderRetrievalByCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    public String getCardNo() {
        return cardNo;
    }
    @Override
    public String toString() {
        return super.toString()+"ByCard# ("+cardNo+")";
    }
}
