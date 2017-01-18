package com.innotec.bats.general;

import java.util.Objects;

/**
 * Created by phoenix on 7/25/16.
 */
public class AccountHolderRetrievalByAccountNo extends AccountHolderRetrieval {
    String accountNo;

    public AccountHolderRetrievalByAccountNo() {
    }

    public AccountHolderRetrievalByAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountHolderRetrievalByAccountNo)) return false;
        AccountHolderRetrievalByAccountNo that = (AccountHolderRetrievalByAccountNo) o;
        return Objects.equals(accountNo, that.accountNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNo);
    }

    @Override
    public String toString() {
        return "AccountHolderRetrievalByAccountNo{" +
                "accountNo='" + accountNo + '\'' +
                '}';
    }
}
