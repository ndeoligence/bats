package com.innotec.bats.server.dao;

import java.sql.SQLException;
import java.util.List;

import com.innotec.bats.general.*;

public interface DAO_Interface {
    AccountHolder getAccountHolderByIdNo(String idNo) throws SQLException;

    /*get cards*/
    AccountHolderCard getAccountHolderCardByCardNo(String cardNo) throws SQLException;

    /*get accounts*/
    /*Adder methods*/
    //teller
    void addAccountHolder(AccountHolder newAccountHolder, String tellerId);

    void addAccountHolderCard(AccountHolderCard newCard);
}
