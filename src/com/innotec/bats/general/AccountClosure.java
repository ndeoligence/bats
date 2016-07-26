package com.innotec.bats.general;

/**
 * Created by phoenix on 7/22/16.
 */
public class AccountClosure extends TellerAction {
    private String accountNo;
    public AccountClosure(String employeeNo, String accountNo) {
        super(employeeNo);
        this.accountNo = accountNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
}
