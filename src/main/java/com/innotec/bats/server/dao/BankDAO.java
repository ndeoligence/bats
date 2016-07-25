package com.innotec.bats.server.dao;

import java.util.List;

import com.innotec.bats.general.*;

/**
 * Created by phoenix on 7/24/16.
 */
public interface BankDAO {
    public List<Card> getCard(String cardNo);
    public List<Account> getAccount(String accountNo);
}
