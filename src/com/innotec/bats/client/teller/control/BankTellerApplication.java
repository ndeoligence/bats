package com.innotec.bats.client.teller.control;

import com.innotec.bats.client.teller.view.TellerHomePage;
import com.innotec.bats.client.teller.view.TellerMainFrame;

public class BankTellerApplication
{
	public static void main(String [] args)
	{
		TellerMainFrame tellerMainFrame = new TellerMainFrame();
		TellerHomePage tellerHomePage = new TellerHomePage(tellerMainFrame.getFramePanel());
		tellerMainFrame.setVisible(true);
	}
}
