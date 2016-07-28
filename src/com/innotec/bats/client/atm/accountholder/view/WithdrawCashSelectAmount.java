package com.innotec.bats.client.atm.accountholder.view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.*;

import com.innotec.bats.client.atm.accountholder.control.ATMApplication;
import com.innotec.bats.client.atm.accountholder.model.ATMUserLogout;
import com.innotec.bats.general.Action;
import com.innotec.bats.general.AccountHolder;
import com.innotec.bats.general.AccountHolderRetrievalByAccountNo;
import com.innotec.bats.general.SessionTermination;
import com.innotec.bats.general.Withdrawal;

public class WithdrawCashSelectAmount extends JPanel implements ActionListener
{
	private JPanel framePanel;
	private AccountHolder accountHolder;
	private String accountNo;
	private JButton btnR100, btnR200, btnR300, btnR500, btnR1000, btnCancel, btnHelp, btnOtherAmount;
	private Action action;
	private boolean waitingPeriod;

public WithdrawCashSelectAmount (JPanel framePanel, AccountHolder accountholder, Action action, boolean waitingPeriod)
{
	this.framePanel = framePanel;
	framePanel.removeAll();
	
	this.accountHolder = accountholder;
	this.action = action;
//	this.accountNo = accountNo;
	this.waitingPeriod = waitingPeriod;
	
	setBackground(SystemColor.inactiveCaption);
	SpringLayout springLayout = new SpringLayout();
	setLayout(springLayout);
	
	
	JPanel panel = new JPanel();
	springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, this);
	springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, this);
	springLayout.putConstraint(SpringLayout.SOUTH, panel, -628, SpringLayout.SOUTH, this);
	springLayout.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, this);
	panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, SystemColor.windowBorder));
	panel.setBackground(SystemColor.inactiveCaption);
	add(panel);
	this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
	SpringLayout sl_panel = new SpringLayout();
	panel.setLayout(sl_panel);
	
	JLabel label = new JLabel("");
	sl_panel.putConstraint(SpringLayout.EAST, label, 1326, SpringLayout.WEST, panel);
	label.setBorder(BorderFactory.createEtchedBorder());
	sl_panel.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.NORTH, panel);
	sl_panel.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, panel);
	label.setIcon(new ImageIcon("resources/NewCityBankLogoSmall.jpg"));
	panel.add(label);
	
	JPanel panel_1 = new JPanel();
	springLayout.putConstraint(SpringLayout.NORTH, panel_1, 6, SpringLayout.SOUTH, panel);
	panel_1.setBackground(SystemColor.inactiveCaption);
	springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -10, SpringLayout.SOUTH, this);
	springLayout.putConstraint(SpringLayout.WEST, panel_1, 10, SpringLayout.WEST, this);
	springLayout.putConstraint(SpringLayout.EAST, panel_1, 1352, SpringLayout.WEST, this);
	panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, SystemColor.windowBorder));
	add(panel_1);
	SpringLayout sl_panel_1 = new SpringLayout();
	panel_1.setLayout(sl_panel_1);
	
	JPanel panel_2 = new JPanel();
	sl_panel_1.putConstraint(SpringLayout.NORTH, panel_2, 84, SpringLayout.NORTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.WEST, panel_2, 232, SpringLayout.WEST, panel_1);
	sl_panel_1.putConstraint(SpringLayout.SOUTH, panel_2, -24, SpringLayout.SOUTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.EAST, panel_2, -233, SpringLayout.EAST, panel_1);
	panel_2.setBackground(SystemColor.inactiveCaption);
	panel_2.setBorder(BorderFactory.createEtchedBorder());
	panel_1.add(panel_2);
	SpringLayout sl_panel_2 = new SpringLayout();
	panel_2.setLayout(sl_panel_2);
	
	btnR200 = new JButton("R200");
	sl_panel_2.putConstraint(SpringLayout.WEST, btnR200, 20, SpringLayout.WEST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnR200, -306, SpringLayout.SOUTH, panel_2);
	btnR200.setIcon(null);
	btnR200.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnR200);
	btnR200.addActionListener(this);
	
	btnHelp = new JButton("Help");
	sl_panel_2.putConstraint(SpringLayout.WEST, btnHelp, 20, SpringLayout.WEST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnHelp, 0, SpringLayout.EAST, btnR200);
	btnHelp.setIcon(new ImageIcon("resources/HelpIcon.jpg"));
	btnHelp.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnHelp);
	btnHelp.addActionListener(this);
	
	btnCancel = new JButton("Cancel");
	sl_panel_2.putConstraint(SpringLayout.WEST, btnCancel, 21, SpringLayout.EAST, btnHelp);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnCancel, -32, SpringLayout.EAST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnHelp, 0, SpringLayout.SOUTH, btnCancel);
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnHelp, 0, SpringLayout.NORTH, btnCancel);
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnCancel, 384, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnCancel, -24, SpringLayout.SOUTH, panel_2);
	btnCancel.setIcon(new ImageIcon("resources/CancelIcon.jpg"));
	btnCancel.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnCancel);
	btnCancel.addActionListener(this);
	
	btnR100 = new JButton("R100");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnR200, 6, SpringLayout.SOUTH, btnR100);
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnR100, 10, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnR100, -400, SpringLayout.SOUTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnR200, 0, SpringLayout.EAST, btnR100);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnR100, 20, SpringLayout.WEST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnR100, -452, SpringLayout.EAST, panel_2);
	btnR100.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnR100);
	btnR100.addActionListener(this);
	
	btnR300 = new JButton("R300");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnR300, 6, SpringLayout.SOUTH, btnR200);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnR300, 0, SpringLayout.WEST, btnR200);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnR300, -212, SpringLayout.SOUTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnR300, -452, SpringLayout.EAST, panel_2);
	btnR300.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnR300);
	btnR300.addActionListener(this);
	
	btnR500 = new JButton("R500");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnR500, -1, SpringLayout.NORTH, btnR100);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnR500, 23, SpringLayout.EAST, btnR100);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnR500, -399, SpringLayout.SOUTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnR500, -32, SpringLayout.EAST, panel_2);
	btnR500.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnR500);
	btnR500.addActionListener(this);
	
	btnOtherAmount = new JButton("Other amount");
	sl_panel_2.putConstraint(SpringLayout.WEST, btnOtherAmount, 23, SpringLayout.EAST, btnR300);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnOtherAmount, -212, SpringLayout.SOUTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnOtherAmount, 188, SpringLayout.NORTH, btnR100);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnOtherAmount, -32, SpringLayout.EAST, panel_2);
	btnOtherAmount.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnOtherAmount);
	btnOtherAmount.addActionListener(this);
	
	btnR1000 = new JButton("R1000");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnR1000, 0, SpringLayout.NORTH, btnR200);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnR1000, 23, SpringLayout.EAST, btnR200);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnR1000, 0, SpringLayout.SOUTH, btnR200);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnR1000, -32, SpringLayout.EAST, panel_2);
	btnR1000.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnR1000);
	btnR1000.addActionListener(this);

	
	JLabel lblWhatWouldYou = new JLabel("Select the amount you would like to withdraw:");
	sl_panel_1.putConstraint(SpringLayout.NORTH, lblWhatWouldYou, 10, SpringLayout.NORTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.WEST, lblWhatWouldYou, 169, SpringLayout.WEST, panel_1);
	lblWhatWouldYou.setFont(new Font("Cambria", Font.PLAIN, 50));
	panel_1.add(lblWhatWouldYou);
	
	framePanel.add(this);
	framePanel.revalidate();
}

@Override
public void actionPerformed (ActionEvent ae)
{
	Object source = ae.getSource();
	
	if (source == btnR100)
	{
		Withdrawal withdrawal = new Withdrawal (accountNo, 100.00, waitingPeriod);
		System.out.println("Withdrawal object created: " + withdrawal.toString());
		this.executeWithdrawal(withdrawal);
	}
	
	if (source == btnR200)
	{
		Withdrawal withdrawal = new Withdrawal (accountNo, 200.00, waitingPeriod);
		System.out.println("Withdrawal object created: " + withdrawal.toString());
		this.executeWithdrawal(withdrawal);
	}
	
	if (source == btnR300)
	{
		Withdrawal withdrawal = new Withdrawal (accountNo, 300.00, waitingPeriod);
		System.out.println("Withdrawal object created: " + withdrawal.toString());
		this.executeWithdrawal(withdrawal);
	}
	
	if (source == btnR500)
	{
		Withdrawal withdrawal = new Withdrawal (accountNo, 500.00, waitingPeriod);
		System.out.println("Withdrawal object created: " + withdrawal.toString());
		this.executeWithdrawal(withdrawal);
	}
	
	if (source == btnR1000)
	{
		Withdrawal withdrawal = new Withdrawal (accountNo, 1000.00, waitingPeriod);
		System.out.println("Withdrawal object created: " + withdrawal.toString());
		this.executeWithdrawal(withdrawal);
	}
	
	if (source == btnOtherAmount)
	{
		new WithdrawCashEnterAmount(framePanel, accountHolder, accountNo, waitingPeriod);
	}
	
}

public void executeWithdrawal (Withdrawal withdrawal)
{
	boolean withdrawalSuccesful = ATMApplication.serverComm.sendWithdrawal(withdrawal);
	
	if (withdrawalSuccesful)
	{
		System.out.println("Withdrawal successfully processed: " + withdrawal.toString());
		JOptionPane.showMessageDialog(null, "Thank you - please collect your cash", "Transaction Complete", JOptionPane.INFORMATION_MESSAGE);
		//Call DNR_Manager methods
		
		if (JOptionPane.showInternalConfirmDialog(null, "Would you like to do another transaction?", "Transaction complete", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
		{
			accountHolder = ATMApplication.serverComm.sendAccountHolderRetrievalByAccountNo(new AccountHolderRetrievalByAccountNo(withdrawal.getPrimAccountNo()));
			new ATMAccountHolderMainMenu(framePanel, accountHolder);
		}
		else
		{
			SessionTermination sessionTermination = new SessionTermination();
			new ATMUserLogout(sessionTermination);
			framePanel.removeAll();
			new ATMWelcomeScreen(framePanel);
		}
	}
}
}
