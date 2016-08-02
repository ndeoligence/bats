package com.innotec.bats.client.teller.control;

import com.innotec.bats.client.atm.control.ATM_ServerComm;
import com.innotec.bats.client.teller.view.TellerHomePage;
import com.innotec.bats.client.teller.view.TellerMainFrame;

public class BankTellerApplication
{
	public static ATM_ServerComm serverComm;
	public static final String tellerID = "chiroptera13801";
	
	public static void main(String [] args)
	{
		serverComm = new ATM_ServerComm();
		
		serverComm.openConnection();
		
		TellerMainFrame tellerMainFrame = new TellerMainFrame();
		TellerHomePage tellerHomePage = new TellerHomePage(tellerMainFrame.getFramePanel());
		tellerMainFrame.setVisible(true);
	}
}

//add client & unblock client
//total deposits & withdrawal amount of notes going in and out - 
