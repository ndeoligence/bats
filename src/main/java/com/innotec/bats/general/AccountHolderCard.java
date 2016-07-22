package com.innotec.bats.general;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phoenix on 7/21/16.
 */
public class AccountHolderCard extends Card {
    public static final int CARD_NO_LEN = 16;
    private List<String> accountNos;
    public AccountHolderCard(String cardNo, String pin, boolean active, List<String> accountNos) {
        super(cardNo, pin, active);
        if (accountNos!=null)
            accountNos = new ArrayList<>(accountNos);
        else accountNos = null;
    }
    public AccountHolderCard(String cardNo, String pin, boolean active) {
        this(cardNo, pin, active,null);
    }
    public List<String> getAccountNos() {return new ArrayList<>(accountNos);}
    public void addAccountNo(String accountNo) {
        accountNos.add(accountNo);
    }
    public String toString() {return super.toString() + " (Bank Account)";}
}
