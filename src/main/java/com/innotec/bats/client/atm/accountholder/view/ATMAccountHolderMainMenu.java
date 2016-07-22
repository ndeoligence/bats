package com.innotec.bats.client.atm.accountholder.view;
import java.awt.SystemColor;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ATMAccountHolderMainMenu extends JPanel implements ActionListener
{

	private JPanel framePanel;
	private JButton btnWithdrawCash, btnViewStatement, btnWithdrawCash_1, btnTransferMoney, btnChangePin, btnHelp, btnViewBalance, btnCancel;

public ATMAccountHolderMainMenu (JPanel framePanel)
{
	framePanel.removeAll();
	this.framePanel = framePanel;
	
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
	label.setIcon(new ImageIcon("C:\\Users\\ilana\\workspace\\BatsGUIs\\resources\\NewCityBankLogoSmall.jpg"));
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
	
	btnWithdrawCash = new JButton("  Deposit cash");
	sl_panel_2.putConstraint(SpringLayout.WEST, btnWithdrawCash, 25, SpringLayout.WEST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnWithdrawCash, -447, SpringLayout.EAST, panel_2);
	btnWithdrawCash.addActionListener(this);
	btnWithdrawCash.setIcon(new ImageIcon("C:\\Users\\ilana\\workspace\\BatsGUIs\\resources\\DepositIcon.jpg"));
	btnWithdrawCash.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnWithdrawCash);
	
	btnWithdrawCash_1 = new JButton("  Withdraw cash");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnWithdrawCash, 4, SpringLayout.SOUTH, btnWithdrawCash_1);
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnWithdrawCash_1, 22, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnWithdrawCash_1, -376, SpringLayout.SOUTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnWithdrawCash_1, -447, SpringLayout.EAST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnWithdrawCash_1, 25, SpringLayout.WEST, panel_2);
	btnWithdrawCash_1.setIcon(new ImageIcon("C:\\Users\\ilana\\workspace\\BatsGUIs\\resources\\WithdrawIcon.jpg"));
	btnWithdrawCash_1.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnWithdrawCash_1);
	btnWithdrawCash_1.addActionListener(this);
	
	btnTransferMoney = new JButton(" Transfer money");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnTransferMoney, 204, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnWithdrawCash, -6, SpringLayout.NORTH, btnTransferMoney);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnTransferMoney, 25, SpringLayout.WEST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnTransferMoney, -194, SpringLayout.SOUTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnTransferMoney, -447, SpringLayout.EAST, panel_2);
	btnTransferMoney.setIcon(new ImageIcon("C:\\Users\\ilana\\workspace\\BatsGUIs\\resources\\TransferIcon.jpg"));
	btnTransferMoney.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnTransferMoney);
	
	btnViewStatement = new JButton("   View statement");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnViewStatement, 0, SpringLayout.NORTH, btnWithdrawCash);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnViewStatement, 0, SpringLayout.SOUTH, btnWithdrawCash);
	btnViewStatement.setIcon(new ImageIcon("C:\\Users\\ilana\\workspace\\BatsGUIs\\resources\\ViewStatementIcon.jpg"));
	btnViewStatement.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnViewStatement);
	
	btnChangePin = new JButton("  Change PIN");
	sl_panel_2.putConstraint(SpringLayout.WEST, btnChangePin, 18, SpringLayout.EAST, btnTransferMoney);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnChangePin, -32, SpringLayout.EAST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnViewStatement, 0, SpringLayout.WEST, btnChangePin);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnViewStatement, 0, SpringLayout.EAST, btnChangePin);
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnChangePin, 0, SpringLayout.NORTH, btnTransferMoney);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnChangePin, 0, SpringLayout.SOUTH, btnTransferMoney);
	btnChangePin.setIcon(new ImageIcon("C:\\Users\\ilana\\workspace\\BatsGUIs\\resources\\ChangePINIcon.jpg"));
	btnChangePin.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnChangePin);
	
	btnHelp = new JButton("Help");
	sl_panel_2.putConstraint(SpringLayout.WEST, btnHelp, 0, SpringLayout.WEST, btnWithdrawCash);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnHelp, 0, SpringLayout.EAST, btnWithdrawCash);
	btnHelp.setIcon(new ImageIcon("C:\\Users\\ilana\\workspace\\BatsGUIs\\resources\\HelpIcon.jpg"));
	btnHelp.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnHelp);
	
	btnViewBalance = new JButton("   View balance");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnViewBalance, 22, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnViewBalance, 0, SpringLayout.WEST, btnChangePin);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnViewBalance, 0, SpringLayout.SOUTH, btnWithdrawCash_1);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnViewBalance, 0, SpringLayout.EAST, btnChangePin);
	btnViewBalance.setIcon(new ImageIcon("C:\\Users\\ilana\\workspace\\BatsGUIs\\resources\\ViewBalanceIcon.jpg"));
	btnViewBalance.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnViewBalance);
	
	btnCancel = new JButton("Cancel");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnHelp, 0, SpringLayout.NORTH, btnCancel);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnHelp, 0, SpringLayout.SOUTH, btnCancel);
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnCancel, 384, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnCancel, 440, SpringLayout.WEST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnCancel, -12, SpringLayout.SOUTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnCancel, -32, SpringLayout.EAST, panel_2);
	btnCancel.setIcon(new ImageIcon("C:\\Users\\ilana\\workspace\\BatsGUIs\\resources\\CancelIcon.jpg"));
	btnCancel.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnCancel);
	
	JLabel lblWhatWouldYou = new JLabel("What would you like to do?");
	sl_panel_1.putConstraint(SpringLayout.NORTH, panel_2, 14, SpringLayout.SOUTH, lblWhatWouldYou);
	sl_panel_1.putConstraint(SpringLayout.NORTH, lblWhatWouldYou, 10, SpringLayout.NORTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.WEST, lblWhatWouldYou, 344, SpringLayout.WEST, panel_1);
	lblWhatWouldYou.setFont(new Font("Cambria", Font.PLAIN, 56));
	panel_1.add(lblWhatWouldYou);
	
	framePanel.add(this);
}
@Override
public void actionPerformed (ActionEvent ae)
{
	Object source = ae.getSource();
	
	if (source == btnWithdrawCash_1)
	{
		WithdrawCashSelectAccount withdrawCashSelectAccount = new WithdrawCashSelectAccount(framePanel);
	}
}
	
}
