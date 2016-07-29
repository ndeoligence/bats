package com.innotec.bats.general;

/**
 * Created by phoenix on 7/25/16.
 */
public class AccountHolderRetrievalByIdNo extends AccountHolderRetrieval {
    String idNo;
    public AccountHolderRetrievalByIdNo(String idNo) {
        this.idNo = idNo;
    }
    public String getIdNo() {
        return idNo;
    }
    @Override
    public String toString() {
        return super.toString()+"ById# ("+ idNo +")";
    }
}
