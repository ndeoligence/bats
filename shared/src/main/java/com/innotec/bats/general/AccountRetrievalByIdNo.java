package com.innotec.bats.general;

import java.util.Objects;

/**
 * Created by phoenix on 7/27/16.
 */
public class AccountRetrievalByIdNo extends AccountRetrieval {
    private String idNo;

    public AccountRetrievalByIdNo() {
    }

    public AccountRetrievalByIdNo(String idNo, String tellerId) {
        super(tellerId);
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
        if (!(o instanceof AccountRetrievalByIdNo)) return false;
        if (!super.equals(o)) return false;
        AccountRetrievalByIdNo that = (AccountRetrievalByIdNo) o;
        return Objects.equals(idNo, that.idNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idNo);
    }

    @Override
    public String toString() {
        return "AccountRetrievalByIdNo{" +
                "idNo='" + idNo + '\'' +
                '}';
    }
}
