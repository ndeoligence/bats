package com.innotec.bats.server.dao;

import com.innotec.bats.general.*;

public class test {

	public static void main(String [] args)
	{
		DAO_Class dao = new DAO_Class();
		ATMAdmin tell = new ATMAdmin("Mark", "Shualt", "1234568734503", "34");
		AccountHolder holder = new AccountHolder("Melize", "Smite", "9701015029089", "2 Jutinga close", "0834014836");
		AccountHolderCard card = new AccountHolderCard("503956778694658", "1234", true, "9701015029089");
		AdminCard cardAdmin = new AdminCard("4593456820349567", "4321", true, "1234568734503");
		
		//dao.addAccountHolder(holder, "1234568734503");
	//	dao.addAccountHolderCard(card);
		//dao.addTeller(tell);
		
		//	dao.addAdmin(cardAdmin);
			holder = dao.getAccountHolderByIdNo("9701015029089");
			//holder = dao.getAccountHolderByCardNo("5039567843094568");
			CurrentAccount account = dao.getCurrentAccount("9701015029089");
			card = dao.getAccountHolderCardByCardNo("503956778694658");
		//	tell = dao.getATMAdmin("1234568734503");
	
		System.out.println(holder);
		System.out.println(card);
		System.out.println(account);
		//System.out.println(tell);
	}
}
