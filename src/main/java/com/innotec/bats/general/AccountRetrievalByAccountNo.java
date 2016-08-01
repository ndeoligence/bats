package com.innotec.bats.general;

/**
 * Created by phoenix on 7/27/16.
 */
public class AccountRetrievalByAccountNo extends AccountRetrieval {
    private String accountNo;

    public AccountRetrievalByAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @Override
    public String toString() {
        return "AccountRetrievalByAccountNo{" +
                "accountNo='" + accountNo + '\'' +
                '}';
    }
}
