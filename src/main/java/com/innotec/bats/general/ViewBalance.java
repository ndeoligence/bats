package com.innotec.bats.general;

/**
 * Created by phoenix on 7/20/16.
 */
public class ViewBalance extends AccountAction {
    public ViewBalance(String accountNo) {
        super(accountNo);
    }
    public String toString() {return "Balance request for " + getAccountNo();}
}
