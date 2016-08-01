package com.innotec.bats.general;
/**
 * Created by phoenix on 7/25/16.
 */
public class AccountHolderRetrievalByAccountNo {
    String accountNo;
    public AccountHolderRetrievalByAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    public String getAccountNo() {
        return accountNo;
    }
    @Override
    public String toString() {
        return super.toString()+"ByAccount# ("+ accountNo +")";
    }
}
