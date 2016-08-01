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
import com.innotec.bats.general.Deposit;
import com.innotec.bats.general.SessionTermination;
import com.innotec.bats.general.Transfer;
import com.innotec.bats.general.Withdrawal;

public class EnterAmount extends JPanel implements ActionListener
{
	private JTextField textField;
	private JPanel framePanel;
	private AccountHolder accountHolder;
	private String accountNo;
	private boolean waitingPeriod;
	private JButton btnOK, btnHelp, btnCancel;;
	private Action action;

public EnterAmount (JPanel framePanel, AccountHolder accountholder, Action action, boolean waitingPeriod)
{
	this.framePanel = framePanel;
	framePanel.removeAll();
	
	this.accountHolder = accountholder;
	this.accountNo = accountNo;
	this.waitingPeriod = waitingPeriod;
	this.action = action;
	
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
	sl_panel_1.putConstraint(SpringLayout.SOUTH, panel_2, -30, SpringLayout.SOUTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.EAST, panel_2, -233, SpringLayout.EAST, panel_1);
	panel_2.setBackground(SystemColor.inactiveCaption);
	panel_2.setBorder(BorderFactory.createEtchedBorder());
	panel_1.add(panel_2);
	SpringLayout sl_panel_2 = new SpringLayout();
	panel_2.setLayout(sl_panel_2);
	
	btnOK = new JButton("OK");
	sl_panel_2.putConstraint(SpringLayout.WEST, btnOK, 236, SpringLayout.WEST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnOK, -157, SpringLayout.SOUTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnOK, -236, SpringLayout.EAST, panel_2);
	btnOK.setIcon(new ImageIcon("resources/YesIcon.jpg"));
	btnOK.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnOK);
	btnOK.addActionListener(this);
	
	btnHelp = new JButton("Help");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnHelp, 55, SpringLayout.SOUTH, btnOK);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnHelp, 20, SpringLayout.WEST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnHelp, -12, SpringLayout.SOUTH, panel_2);
	btnHelp.setIcon(new ImageIcon("resources/HelpIcon.jpg"));
	btnHelp.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnHelp);
	
	btnCancel = new JButton("Cancel");
	sl_panel_2.putConstraint(SpringLayout.EAST, btnHelp, -23, SpringLayout.WEST, btnCancel);
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnCancel, 391, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnCancel, 440, SpringLayout.WEST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnCancel, -12, SpringLayout.SOUTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnCancel, -32, SpringLayout.EAST, panel_2);
	btnCancel.setIcon(new ImageIcon("resources/CancelIcon.jpg"));
	btnCancel.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnCancel);
	
	JLabel lblR = new JLabel("R");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnOK, 54, SpringLayout.SOUTH, lblR);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, lblR, -298, SpringLayout.SOUTH, panel_2);
	lblR.setFont(new Font("Cambria", Font.PLAIN, 70));
	panel_2.add(lblR);
	
	textField = new JTextField();
	sl_panel_2.putConstraint(SpringLayout.NORTH, textField, 112, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, textField, -66, SpringLayout.NORTH, btnOK);
	sl_panel_2.putConstraint(SpringLayout.EAST, lblR, -73, SpringLayout.WEST, textField);
	sl_panel_2.putConstraint(SpringLayout.WEST, textField, 318, SpringLayout.WEST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, textField, -194, SpringLayout.EAST, panel_2);
	textField.setFont(new Font("Calibri", Font.PLAIN, 54));
	panel_2.add(textField);
	textField.setColumns(10);
	
	JLabel lblWhatWouldYou = new JLabel("Enter the amount you would like to withdraw:");
	sl_panel_1.putConstraint(SpringLayout.NORTH, lblWhatWouldYou, 10, SpringLayout.NORTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.WEST, lblWhatWouldYou, 173, SpringLayout.WEST, panel_1);
	lblWhatWouldYou.setFont(new Font("Cambria", Font.PLAIN, 50));
	panel_1.add(lblWhatWouldYou);
	
	framePanel.add(this);
	framePanel.revalidate();
}

@Override
public void actionPerformed (ActionEvent ae)
{
	Object source = ae.getSource();
	
	if (source == btnOK)
	{
		double enteredAmount = Double.parseDouble(textField.getText());
		
		if (action instanceof Withdrawal)
		{
			if (enteredAmount%10!=0)
			{
				JOptionPane.showMessageDialog(null, "The amount you have entered is invalid. Please enter an amount in increments of ten.", "Invalid amount", JOptionPane.INFORMATION_MESSAGE);
			}
			else
				if (enteredAmount<10.0)
				{
					JOptionPane.showMessageDialog(null, "The amount you have entered is invalid. Please enter an amount larger than R10.00.", "Invalid amount", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					Withdrawal withdrawal = new Withdrawal (accountNo, enteredAmount, waitingPeriod);
					System.out.println("Withdrawal object created: " + withdrawal.toString());
					this.executeWithdrawal(withdrawal);
				}
		}
		
		if (action instanceof Deposit)
		{
			((Deposit) action).setAmount(Double.parseDouble(textField.getText()));
			this.executeDeposit((Deposit)action);
		}
		
		if (action instanceof Transfer)
		{
			((Transfer)action).setAmount(Double.parseDouble(textField.getText()));
			this.executeTransfer((Transfer)action);
		}
	}
	
	if (source == btnHelp)
	{
		if (action instanceof Deposit)
		{
			new HelpShowFile(framePanel, new ImageIcon("resources/Help File Deposit.jpg"), accountHolder);
		}
		if (action instanceof Withdrawal)
		{
			new HelpShowFile(framePanel, new ImageIcon("resources/Help File Withdrawal.jpg"), accountHolder);
		}
		if (action instanceof Transfer)
		{
			new HelpShowFile(framePanel, new ImageIcon("resources/Help File Transfer.jpg"), accountHolder);
		}
	}
	
	if (source == btnCancel)
	{
		new ATMAccountHolderMainMenu(framePanel, accountHolder);
	}
}

public boolean executeWithdrawal (Withdrawal withdrawal)
{
	boolean withdrawalSuccesful = ATMApplication.serverComm.sendWithdrawal(withdrawal);
	
	if (withdrawalSuccesful)
	{
		System.out.println("Withdrawal successfully processed: " + withdrawal.toString());
		JOptionPane.showMessageDialog(null, "Withdrawal successfully processed.", "Status", JOptionPane.INFORMATION_MESSAGE);
		//DNR_Manager methods
		
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
	return withdrawalSuccesful;
}

public void executeDeposit(Deposit deposit)
{
	new DepositInsertEnvelope(framePanel, accountHolder, deposit);
}

public void executeTransfer(Transfer transfer)
{
	if (ATMApplication.serverComm.sendTransfer(transfer))
	{
		JOptionPane.showMessageDialog(null, "Transfer successfully processed", "Transaction Completed", JOptionPane.INFORMATION_MESSAGE);
		accountHolder = ATMApplication.serverComm.sendAccountHolderRetrievalByAccountNo(new AccountHolderRetrievalByAccountNo(transfer.getPrimAccountNo()));
		new ATMAccountHolderMainMenu(framePanel, accountHolder);
	}
}



}
