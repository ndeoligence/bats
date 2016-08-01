package com.innotec.bats.client.atm.accountholder.view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.*;


public class HelpChooseTopic extends JPanel implements ActionListener
{
	private JPanel framePanel;
	private ImageIcon withdrawalFile, depositFile, transferFile, viewBalanceFile, viewStatementFile, changePINFile, guidelinesFile; 
	private JButton btnDepositingCash, btnWithdrawingCash, btnTransferMoney, btnViewStatement, btnChangePin, btnViewBalance, btnCancel, btnSecurityGuidelines;

public HelpChooseTopic (JPanel framePanel)
{
	this.framePanel = framePanel;
	framePanel.removeAll();
	
	withdrawalFile = new ImageIcon("resources/Help File Withdrawal.jpg");
	depositFile = new ImageIcon("resources/Help File Deposit.jpg");
	transferFile = new ImageIcon("resources/Help File Transfer.jpg");
	viewBalanceFile = new ImageIcon("resources/Help File View Balance.jpg");
	viewStatementFile = new ImageIcon("resources/Help File View Statement.jpg");
	changePINFile = new ImageIcon("resources/Help File Change PIN.jpg");
	guidelinesFile = new ImageIcon("resources/Help File Security Guidelines.jpg");
	
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
	sl_panel_1.putConstraint(SpringLayout.WEST, panel_2, 232, SpringLayout.WEST, panel_1);
	sl_panel_1.putConstraint(SpringLayout.SOUTH, panel_2, -30, SpringLayout.SOUTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.EAST, panel_2, -233, SpringLayout.EAST, panel_1);
	panel_2.setBackground(SystemColor.inactiveCaption);
	panel_2.setBorder(BorderFactory.createEtchedBorder());
	panel_1.add(panel_2);
	SpringLayout sl_panel_2 = new SpringLayout();
	panel_2.setLayout(sl_panel_2);
	
	btnDepositingCash = new JButton("Depositing cash");
	sl_panel_2.putConstraint(SpringLayout.WEST, btnDepositingCash, 25, SpringLayout.WEST, panel_2);
	btnDepositingCash.addActionListener(this);
	
	btnDepositingCash.setIcon(new ImageIcon("resources/DepositIcon.jpg"));
	btnDepositingCash.setFont(new Font("Cambria", Font.PLAIN, 36));
	panel_2.add(btnDepositingCash);
	
	btnWithdrawingCash = new JButton(" Withdrawing cash");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnDepositingCash, 2, SpringLayout.SOUTH, btnWithdrawingCash);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnWithdrawingCash, 0, SpringLayout.EAST, btnDepositingCash);
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnWithdrawingCash, 22, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnWithdrawingCash, -376, SpringLayout.SOUTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnWithdrawingCash, 25, SpringLayout.WEST, panel_2);
	btnWithdrawingCash.setIcon(new ImageIcon("resources/WithdrawIcon.jpg"));
	btnWithdrawingCash.setFont(new Font("Cambria", Font.PLAIN, 36));
	panel_2.add(btnWithdrawingCash);
	btnWithdrawingCash.addActionListener(this);
	
	btnTransferMoney = new JButton("Transferring money");
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnDepositingCash, -8, SpringLayout.NORTH, btnTransferMoney);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnDepositingCash, 0, SpringLayout.EAST, btnTransferMoney);
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnTransferMoney, 204, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnTransferMoney, -443, SpringLayout.EAST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnTransferMoney, 25, SpringLayout.WEST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnTransferMoney, -194, SpringLayout.SOUTH, panel_2);
	btnTransferMoney.setIcon(new ImageIcon("resources/TransferIcon.jpg"));
	btnTransferMoney.setFont(new Font("Cambria", Font.PLAIN, 34));
	panel_2.add(btnTransferMoney);
	btnTransferMoney.addActionListener(this);
	
	btnViewStatement = new JButton(" Viewing a statement");
	btnViewStatement.setIcon(new ImageIcon("resources/ViewStatementIcon.jpg"));
	btnViewStatement.setFont(new Font("Cambria", Font.PLAIN, 34));
	panel_2.add(btnViewStatement);
	btnViewStatement.addActionListener(this);
	
	btnChangePin = new JButton(" Changing your PIN");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnChangePin, 202, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnViewStatement, -6, SpringLayout.NORTH, btnChangePin);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnChangePin, 12, SpringLayout.EAST, btnTransferMoney);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnChangePin, -32, SpringLayout.EAST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnViewStatement, 0, SpringLayout.WEST, btnChangePin);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnViewStatement, 0, SpringLayout.EAST, btnChangePin);
	btnChangePin.setIcon(new ImageIcon("resources/ChangePINIcon.jpg"));
	btnChangePin.setFont(new Font("Cambria", Font.PLAIN, 36));
	panel_2.add(btnChangePin);
	btnChangePin.addActionListener(this);
	
	btnViewBalance = new JButton("   Viewing a balance");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnViewBalance, 22, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnViewBalance, -376, SpringLayout.SOUTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnViewStatement, 4, SpringLayout.SOUTH, btnViewBalance);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnViewBalance, 0, SpringLayout.WEST, btnChangePin);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnViewBalance, 0, SpringLayout.EAST, btnChangePin);
	btnViewBalance.setIcon(new ImageIcon("resources/ViewBalanceIcon.jpg"));
	btnViewBalance.setFont(new Font("Cambria", Font.PLAIN, 34));
	panel_2.add(btnViewBalance);
	btnViewBalance.addActionListener(this);
	
	btnCancel = new JButton("Cancel");
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnChangePin, -95, SpringLayout.NORTH, btnCancel);
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnCancel, 384, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnCancel, 440, SpringLayout.WEST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnCancel, -12, SpringLayout.SOUTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnCancel, -32, SpringLayout.EAST, panel_2);
	btnCancel.setIcon(new ImageIcon("resources/CancelIcon.jpg"));
	btnCancel.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnCancel);
	btnCancel.addActionListener(this);
	
	JLabel lblWhatWouldYou = new JLabel("What do you need help with?");
	sl_panel_1.putConstraint(SpringLayout.NORTH, panel_2, 14, SpringLayout.SOUTH, lblWhatWouldYou);
	
	btnSecurityGuidelines = new JButton("Security guidelines");
	btnSecurityGuidelines.setIcon(new ImageIcon("resources/SecurityIcon.jpg"));
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnSecurityGuidelines, 0, SpringLayout.NORTH, btnCancel);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnSecurityGuidelines, 0, SpringLayout.WEST, btnDepositingCash);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnSecurityGuidelines, 0, SpringLayout.SOUTH, btnCancel);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnSecurityGuidelines, 0, SpringLayout.EAST, btnDepositingCash);
	btnSecurityGuidelines.setFont(new Font("Cambria", Font.PLAIN, 34));
	panel_2.add(btnSecurityGuidelines);
	btnSecurityGuidelines.addActionListener(this);
	
	sl_panel_1.putConstraint(SpringLayout.NORTH, lblWhatWouldYou, 10, SpringLayout.NORTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.WEST, lblWhatWouldYou, 323, SpringLayout.WEST, panel_1);
	lblWhatWouldYou.setFont(new Font("Cambria", Font.PLAIN, 56));
	panel_1.add(lblWhatWouldYou);
	
	framePanel.add(this);
	framePanel.revalidate();
}

@Override
public void actionPerformed (ActionEvent ae)
{
	Object source = ae.getSource();
	
	if (source == btnWithdrawingCash)
	{
		new HelpShowFile(framePanel, withdrawalFile);
	}
	
	if (source == btnDepositingCash)
	{
		new HelpShowFile(framePanel, depositFile);
	}
	
	if (source == btnTransferMoney)
	{
		new HelpShowFile(framePanel, transferFile);
	}
	
	if (source == btnViewBalance)
	{
		new HelpShowFile(framePanel, viewBalanceFile);
	}
	
	if (source == btnViewStatement)
	{
		new HelpShowFile(framePanel, viewStatementFile);
	}
	
	if (source == btnChangePin)
	{
		new HelpShowFile(framePanel, changePINFile);
	}
	
	if (source == btnSecurityGuidelines)
	{
		new HelpShowFile(framePanel, guidelinesFile);
	}
	
	if (source == btnCancel)
	{
		new ATMAccountHolderMainMenu(framePanel, null);
	}
	
}

}
