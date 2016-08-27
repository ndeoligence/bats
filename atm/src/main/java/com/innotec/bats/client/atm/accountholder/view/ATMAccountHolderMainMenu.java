package com.innotec.bats.client.atm.accountholder.view;

import java.awt.SystemColor;

import javax.swing.*;

import java.awt.*;

import javax.swing.border.*;

import com.innotec.bats.client.atm.accountholder.model.ATMUserLogout;
import com.innotec.bats.general.AccountHolder;
import com.innotec.bats.general.SessionTermination;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ATMAccountHolderMainMenu extends JPanel implements ActionListener {

	private JPanel framePanel;
	private JButton btnDepositCash, btnViewStatement, btnWithdrawCash, btnTransferMoney, btnChangePin, btnHelp,
			btnViewBalance, btnCancel;
	private AccountHolder accountHolder;

	public ATMAccountHolderMainMenu(JPanel framePanel, AccountHolder accountHolder) {
		framePanel.removeAll();
		this.framePanel = framePanel;

		this.accountHolder = accountHolder;

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

		btnDepositCash = new JButton("  Deposit cash");
		sl_panel_2.putConstraint(SpringLayout.WEST, btnDepositCash, 25, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnDepositCash, -447, SpringLayout.EAST, panel_2);
		btnDepositCash.addActionListener(this);
		btnDepositCash.setIcon(new ImageIcon("resources/DepositIcon.jpg"));
		btnDepositCash.setFont(new Font("Cambria", Font.PLAIN, 38));
		panel_2.add(btnDepositCash);
		btnDepositCash.addActionListener(this);

		btnWithdrawCash = new JButton("  Withdraw cash");
		sl_panel_2.putConstraint(SpringLayout.NORTH, btnDepositCash, 4, SpringLayout.SOUTH, btnWithdrawCash);
		sl_panel_2.putConstraint(SpringLayout.NORTH, btnWithdrawCash, 22, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnWithdrawCash, -376, SpringLayout.SOUTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnWithdrawCash, -447, SpringLayout.EAST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, btnWithdrawCash, 25, SpringLayout.WEST, panel_2);
		btnWithdrawCash.setIcon(new ImageIcon("resources/WithdrawIcon.jpg"));
		btnWithdrawCash.setFont(new Font("Cambria", Font.PLAIN, 38));
		panel_2.add(btnWithdrawCash);
		btnWithdrawCash.addActionListener(this);

		btnTransferMoney = new JButton(" Transfer money");
		sl_panel_2.putConstraint(SpringLayout.NORTH, btnTransferMoney, 204, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnDepositCash, -6, SpringLayout.NORTH, btnTransferMoney);
		sl_panel_2.putConstraint(SpringLayout.WEST, btnTransferMoney, 25, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnTransferMoney, -194, SpringLayout.SOUTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnTransferMoney, -447, SpringLayout.EAST, panel_2);
		btnTransferMoney.setIcon(new ImageIcon("resources/TransferIcon.jpg"));
		btnTransferMoney.setFont(new Font("Cambria", Font.PLAIN, 38));
		panel_2.add(btnTransferMoney);
		btnTransferMoney.addActionListener(this);

		btnViewStatement = new JButton("   View statement");
		sl_panel_2.putConstraint(SpringLayout.NORTH, btnViewStatement, 0, SpringLayout.NORTH, btnDepositCash);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnViewStatement, 0, SpringLayout.SOUTH, btnDepositCash);
		btnViewStatement.setIcon(new ImageIcon("resources/ViewStatementIcon.jpg"));
		btnViewStatement.setFont(new Font("Cambria", Font.PLAIN, 38));
		panel_2.add(btnViewStatement);
		btnViewStatement.addActionListener(this);

		btnChangePin = new JButton("  Change PIN");
		sl_panel_2.putConstraint(SpringLayout.WEST, btnChangePin, 18, SpringLayout.EAST, btnTransferMoney);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnChangePin, -32, SpringLayout.EAST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, btnViewStatement, 0, SpringLayout.WEST, btnChangePin);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnViewStatement, 0, SpringLayout.EAST, btnChangePin);
		sl_panel_2.putConstraint(SpringLayout.NORTH, btnChangePin, 0, SpringLayout.NORTH, btnTransferMoney);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnChangePin, 0, SpringLayout.SOUTH, btnTransferMoney);
		btnChangePin.setIcon(new ImageIcon("resources/ChangePINIcon.jpg"));
		btnChangePin.setFont(new Font("Cambria", Font.PLAIN, 38));
		panel_2.add(btnChangePin);
		btnChangePin.addActionListener(this);

		btnHelp = new JButton("Help");
		sl_panel_2.putConstraint(SpringLayout.WEST, btnHelp, 0, SpringLayout.WEST, btnDepositCash);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnHelp, 0, SpringLayout.EAST, btnDepositCash);
		btnHelp.setIcon(new ImageIcon("resources/HelpIcon.jpg"));
		btnHelp.setFont(new Font("Cambria", Font.PLAIN, 38));
		panel_2.add(btnHelp);
		btnHelp.addActionListener(this);

		btnViewBalance = new JButton("   View balance");
		sl_panel_2.putConstraint(SpringLayout.NORTH, btnViewBalance, 22, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, btnViewBalance, 0, SpringLayout.WEST, btnChangePin);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnViewBalance, 0, SpringLayout.SOUTH, btnWithdrawCash);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnViewBalance, 0, SpringLayout.EAST, btnChangePin);
		btnViewBalance.setIcon(new ImageIcon("resources/ViewBalanceIcon.jpg"));
		btnViewBalance.setFont(new Font("Cambria", Font.PLAIN, 38));
		panel_2.add(btnViewBalance);
		btnViewBalance.addActionListener(this);

		btnCancel = new JButton("Cancel");
		sl_panel_2.putConstraint(SpringLayout.NORTH, btnHelp, 0, SpringLayout.NORTH, btnCancel);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnHelp, 0, SpringLayout.SOUTH, btnCancel);
		sl_panel_2.putConstraint(SpringLayout.NORTH, btnCancel, 384, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, btnCancel, 440, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnCancel, -12, SpringLayout.SOUTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnCancel, -32, SpringLayout.EAST, panel_2);
		btnCancel.setIcon(new ImageIcon("resources/CancelIcon.jpg"));
		btnCancel.setFont(new Font("Cambria", Font.PLAIN, 38));
		panel_2.add(btnCancel);
		btnCancel.addActionListener(this);

		JLabel lblWhatWouldYou = new JLabel("What would you like to do?");
		sl_panel_1.putConstraint(SpringLayout.NORTH, panel_2, 14, SpringLayout.SOUTH, lblWhatWouldYou);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblWhatWouldYou, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblWhatWouldYou, 344, SpringLayout.WEST, panel_1);
		lblWhatWouldYou.setFont(new Font("Cambria", Font.PLAIN, 56));
		panel_1.add(lblWhatWouldYou);

		this.setVisible(true);
		framePanel.add(this);
		framePanel.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();

		if (source == btnWithdrawCash) {
			new WithdrawCashSelectAccount(framePanel, accountHolder.getAccounts());
		}

		if (source == btnDepositCash) {
			new DepositSelectAccount(framePanel);
		}

		if (source == btnTransferMoney) {

		}

		if (source == btnViewBalance) {

		}

		if (source == btnViewStatement) {

		}

		if (source == btnHelp) {
			new HelpChooseTopic(framePanel);
		}

		if (source == btnCancel) {
			SessionTermination sessionTermination = new SessionTermination();
			new ATMUserLogout(sessionTermination);
			framePanel.removeAll();
			new ATMWelcomeScreen(framePanel);
		}
	}

}
