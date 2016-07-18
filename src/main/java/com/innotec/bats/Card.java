package com.innotec.bats;

import java.io.Serializable;
import java.util.List;

/**
 * Created by phoenix on 7/18/16.
 */
public class Card implements Serializable {
    private String cardNo;
    private String pin;
    private List<BankAccount> accounts;
    public Card() {

    }
}
