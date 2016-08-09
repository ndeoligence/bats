package com.innotec.bats.client.atm.accountholder.model;

import com.innotec.bats.client.atm.accountholder.control.ATMApplication;
import com.innotec.bats.client.atm.control.ATM_ServerComm;
import com.innotec.bats.general.SessionTermination;

public class ATMUserLogout
{
		public ATMUserLogout (SessionTermination sessionTermination)
		{
			ATMApplication.serverComm.sendSessionTermination(sessionTermination);
			ATMApplication.serverComm.closeConnection();
			this.ejectCard();
		}
		
		public boolean ejectCard ()
		{
			return true;
		}
}
