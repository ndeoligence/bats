package com.innotec.bat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phoenix on 7/18/16.
 */
public class AccountHolder extends Person implements CardUser {
    private String address;
    private String contactNo;
    List<BankAccount> accounts;
    public AccountHolder(String name, String surname, String idNo, Gender gender, String address, String contactNo) {
        super(name,surname,idNo,gender);
        if (address==null || contactNo==null)
            throw new IllegalArgumentException("null AccountHolder fields");
        this.address = new String(address);
        this.contactNo = new String(contactNo);
        accounts = new ArrayList<>();
    }
}
