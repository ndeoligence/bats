package com.innotec.bats.general;

/**
 * Created by phoenix on 7/27/16.
 */
public class AccountRetrievalByIdNo extends AccountRetrieval {
    private String idNo;

    public AccountRetrievalByIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    @Override
    public String toString() {
        return "AccountRetrievalByIdNo{" +
                "idNo='" + idNo + '\'' +
                '}';
    }
}
