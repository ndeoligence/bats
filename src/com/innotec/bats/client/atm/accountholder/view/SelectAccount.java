package com.innotec.bats.client.atm.accountholder.view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.*;

import com.innotec.bats.client.atm.accountholder.control.ATMApplication;
import com.innotec.bats.client.atm.accountholder.model.ATMUserLogout;
import com.innotec.bats.general.Action;
import com.innotec.bats.general.*;
import com.innotec.bats.general.AccountHolder;
import com.innotec.bats.general.AccountHolderRetrievalByAccountNo;
import com.innotec.bats.general.CurrentAccount;
import com.innotec.bats.general.SavingsAccount;
import com.innotec.bats.general.SessionTermination;
import com.innotec.bats.general.Transaction;
import com.innotec.bats.general.Withdrawal;

public class SelectAccount extends JPanel implements ActionListener
{
	private JPanel framePanel;
	private AccountHolder accountHolder;
	private boolean currentAccountActive, savingsAccountActive; 
	private ArrayList accounts;
	private JButton btnSavingsAccount, btnCurrentAccount, btnHelp, btnCancel;
	private CurrentAccount currentAccount;
	private SavingsAccount savingsAccount;
	private Action action;

public SelectAccount (JPanel framePanel, AccountHolder accountHolder, Action action, String actionWord)
{
	this.framePanel = framePanel;
	framePanel.removeAll();
	
	this.action = action;
	this.accountHolder = accountHolder;
	this.accounts = accountHolder.getAccounts();
	
	currentAccountActive = false;
	savingsAccountActive = false;
	
	for (int i=0; i < accounts.size(); i++)
	{
		if (accounts.get(i) instanceof CurrentAccount)
		{
			currentAccountActive = true;
			currentAccount = (CurrentAccount)accounts.get(i);
		}
		if (accounts.get(i) instanceof SavingsAccount)
		{
			savingsAccountActive = true;
			savingsAccount = (SavingsAccount)accounts.get(i);
		}
	}
	
	
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
	
	btnSavingsAccount = new JButton("Savings Account");
	sl_panel_2.putConstraint(SpringLayout.EAST, btnSavingsAccount, -32, SpringLayout.EAST, panel_2);
	btnSavingsAccount.setIcon(null);
	btnSavingsAccount.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnSavingsAccount);
	if (!savingsAccountActive)
	{
		btnSavingsAccount.setEnabled(false);
	}
	btnSavingsAccount.addActionListener(this);
	
	btnCurrentAccount = new JButton("Current account");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnSavingsAccount, 0, SpringLayout.NORTH, btnCurrentAccount);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnSavingsAccount, 18, SpringLayout.EAST, btnCurrentAccount);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnSavingsAccount, 0, SpringLayout.SOUTH, btnCurrentAccount);
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnCurrentAccount, 22, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnCurrentAccount, -376, SpringLayout.SOUTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnCurrentAccount, -447, SpringLayout.EAST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnCurrentAccount, 25, SpringLayout.WEST, panel_2);
	btnCurrentAccount.setIcon(null);
	btnCurrentAccount.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnCurrentAccount);
	if (!currentAccountActive)
	{
		btnCurrentAccount.setEnabled(false);
	}
	btnCurrentAccount.addActionListener(this);
	
	JButton btnCreditAccount = new JButton("Credit card account");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnCreditAccount, 6, SpringLayout.SOUTH, btnCurrentAccount);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnCreditAccount, 0, SpringLayout.WEST, btnCurrentAccount);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnCreditAccount, -278, SpringLayout.SOUTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnCreditAccount, -447, SpringLayout.EAST, panel_2);
	btnCreditAccount.setIcon(null);
	btnCreditAccount.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnCreditAccount);
	btnCreditAccount.setEnabled(false);
	
	btnHelp = new JButton("Help");
	sl_panel_2.putConstraint(SpringLayout.WEST, btnHelp, 0, SpringLayout.WEST, btnCurrentAccount);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnHelp, 0, SpringLayout.EAST, btnCurrentAccount);
	btnHelp.setIcon(new ImageIcon("resources/HelpIcon.jpg"));
	btnHelp.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnHelp);
	btnHelp.addActionListener(this);
	
	btnCancel = new JButton("Cancel");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnHelp, 0, SpringLayout.NORTH, btnCancel);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnHelp, 0, SpringLayout.SOUTH, btnCancel);
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnCancel, 391, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnCancel, 440, SpringLayout.WEST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnCancel, -12, SpringLayout.SOUTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnCancel, -32, SpringLayout.EAST, panel_2);
	btnCancel.setIcon(new ImageIcon("resources/CancelIcon.jpg"));
	btnCancel.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnCancel);
	btnCancel.addActionListener(this);
	
	JLabel lblWhatWouldYou = new JLabel("Select the account for " + actionWord + " :");
	sl_panel_1.putConstraint(SpringLayout.NORTH, lblWhatWouldYou, 10, SpringLayout.NORTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.WEST, lblWhatWouldYou, 110, SpringLayout.WEST, panel_1);
	lblWhatWouldYou.setFont(new Font("Cambria", Font.PLAIN, 50));
	panel_1.add(lblWhatWouldYou);
	
	framePanel.add(this);
	framePanel.revalidate();
}

@Override
public void actionPerformed (ActionEvent ae)
{
	Object source = ae.getSource();
	
	if (source == btnCurrentAccount)
	{
		if (action instanceof Withdrawal)
		{
			((Withdrawal)action).setPrimAccountNo(currentAccount.getAccountNo());
			new WithdrawCashSelectAmount(framePanel, accountHolder, action, false);
			System.out.println("Withdrawal started. Account: " + currentAccount.toString());
		}
		
		if (action instanceof Deposit)
		{
			((Deposit)action).setPrimAccountNo(currentAccount.getAccountNo());
			new WithdrawCashSelectAmount(framePanel, accountHolder, action, false);
			System.out.println("Withdrawal started. Account: " + currentAccount.toString());
		}
		
		if (action instanceof Transfer)
		{
			if ((((Transfer) action).getPrimAccountNo() == null) || (((Transfer) action).getPrimAccountNo() == ""))
			{
				((Transfer)action).setPrimAccountNo(currentAccount.getAccountNo());
				new SelectAccount(framePanel, accountHolder, action, "transfer TO");
				System.out.println("Withdrawal started. Account: " + currentAccount.toString());
			}
			else
			{
				((Transfer)action).setSecondaryAccountNumber(currentAccount.getAccountNo());
				new WithdrawCashSelectAmount(framePanel, accountHolder, action, false);
				System.out.println("Withdrawal started. Account: " + currentAccount.toString());
			}
		}
		
		if (action instanceof ViewBalance)
		{
			
		}
	}
	
	if (source == btnSavingsAccount)
	{
		if (savingsAccount.getWithdrawalPending())
		{
			if (savingsAccount.getFundsAvailableDate().after(new Date()))
			{
				JOptionPane.showMessageDialog(null, "You already have a withdrawal pending on this account. The amount of " + savingsAccount.getPendingWithdrawalAmount() + " will be available on " + savingsAccount.getFundsAvailableDate());
			}
			else
			{
				if (JOptionPane.showInternalConfirmDialog(null, "You have R" + savingsAccount.getPendingWithdrawalAmount() + " available for withdrawal. Would you like to withdraw this amount now?", "Confirm Withdraw", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				{
					if (this.executeWithdrawal(new Withdrawal(savingsAccount.getAccountNo(), savingsAccount.getPendingWithdrawalAmount(), true)))
					{
						//Call DNRManager methods
						
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You will be returned to the main menu");
					new ATMAccountHolderMainMenu(framePanel, accountHolder);
				}
			}
		}
		else
		{
			new WithdrawCashSelectAmount(framePanel, accountHolder, savingsAccount.getAccountNo(), true);
			System.out.println("Withdrawal started. Account: " + savingsAccount.toString());
		}
	}
	
	if (source == btnHelp)
	{
		new HelpShowFile(framePanel, new ImageIcon("resources/Help File Withdrawal.jpg"), accountHolder);
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
		JOptionPane.showMessageDialog(null, "Thank you - please collect your cash", "Transaction Complete", JOptionPane.INFORMATION_MESSAGE);
		//DNR_Manager methods
		
		if (JOptionPane.showInternalConfirmDialog(null, "Would you like to do another transaction?", "Transaction Complete", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
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
		return true;
	}
	return false;
}
}
