package com.innotec.bats.client.atm.accountholder.control;

import com.innotec.bats.client.atm.accountholder.view.MainGUI;
import com.innotec.bats.client.atm.accountholder.view.ATMWelcomeScreen;
//import com.innotec.bats.client.atm.control.ATM_ServerComm;
//import com.innotec.bats.general.Action;
import com.innotec.bats.client.atm.control.ATM_ServerComm;

public class ATMApplication
{

	public static ATM_ServerComm serverComm;

	public static void main (String[] args)
	{
		serverComm = new ATM_ServerComm();
		
		MainGUI mainGUI = new MainGUI();		
		
		ATMWelcomeScreen atmWelcomeScreen = new ATMWelcomeScreen(mainGUI.getFramePanel());
		mainGUI.setVisible(true);
		


	}


}
