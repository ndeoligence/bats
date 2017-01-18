package com.innotec.bats.general;

import java.util.Objects;

/**
 * Created by phoenix on 7/25/16.
 */
public class AccountHolderRetrievalByIdNo extends AccountHolderRetrieval {
    String idNo;

    public AccountHolderRetrievalByIdNo() {
    }

    public AccountHolderRetrievalByIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountHolderRetrievalByIdNo)) return false;
        AccountHolderRetrievalByIdNo that = (AccountHolderRetrievalByIdNo) o;
        return Objects.equals(idNo, that.idNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNo);
    }

    @Override
    public String toString() {
        return "AccountHolderRetrievalByIdNo{" +
                "idNo='" + idNo + '\'' +
                '}';
    }
}
