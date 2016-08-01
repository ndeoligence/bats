package com.innotec.bats.server.dao;

import java.util.List;

import com.innotec.bats.general.*;

/**
 * Created by phoenix on 7/24/16.
 */
public interface BankDAO {
    public Card getAdminCard(String cardNo);
    public Card getAccountHolderCard(String cardNo);
    public Account getAccount(String accountNo);
    public List<Account> getAccountsByIdNo(String idNo);
    public List<Account> getAccountsByCardNo(String cardNo);
}
