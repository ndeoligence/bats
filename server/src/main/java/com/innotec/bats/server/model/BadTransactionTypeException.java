package com.innotec.bats.server.model;

/**
 * Created by phoenix on 8/9/16.
 */
public class BadTransactionTypeException extends BankException {
    public BadTransactionTypeException(String s) {
        super(s);
    }
}
