package com.innotec.bats.server.dao;

import com.innotec.bats.general.AccountHolderCard;
import com.innotec.bats.general.AdminCard;

import java.sql.SQLException;

public interface DAO_Interface
{

//	void addAdmin(AdminCard newAdmin) throws SQLException;

	/*get cards*/
	AccountHolderCard getAccountHolderCardByCardNo(String cardNo) throws SQLException;
}
