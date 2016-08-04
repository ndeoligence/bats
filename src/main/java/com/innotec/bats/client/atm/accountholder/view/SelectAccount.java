package com.innotec.bats.client.atm.accountholder.view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.border.*;

import com.innotec.bats.client.atm.accountholder.control.ATMApplication;
import com.innotec.bats.client.atm.accountholder.model.ATMUserLogout;
import com.innotec.bats.general.Action;
import com.innotec.bats.general.*;

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
	private Account account;

public SelectAccount (JPanel framePanel, AccountHolder accountHolder, Action action, String actionWord)
{
	this.framePanel = framePanel;
	framePanel.removeAll();
	
	this.action = action;
	this.accountHolder = accountHolder;
	this.accounts = accountHolder.getAccounts();
	
	currentAccountActive = false;
	savingsAccountActive = false;
	
	System.out.println("Displaying accountholder from SelectAccount GUI: " + accountHolder.toString() + accountHolder.getCard().toString() +
			accountHolder.getAccounts().toString());
	
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
		account = currentAccount;
		
		if (action instanceof Withdrawal)
		{
			((Withdrawal)action).setPrimAccountNo(account.getAccountNo());
			this.executeCurrentAccountWithdrawal((Withdrawal)action);
		}
		
		if (action instanceof Deposit)
		{
			((Deposit)action).setPrimAccountNo(account.getAccountNo());
			this.executeDeposit((Deposit)action);
		}
		
		if (action instanceof Transfer)
		{
			
			if ((((Transfer) action).getPrimAccountNo() == null) || (((Transfer) action).getPrimAccountNo() == ""))
			{
				((Transfer)action).setPrimAccountNo(account.getAccountNo());
				this.executeTransferPrimary((Transfer)action);
			}
			else
			{
				((Transfer)action).setSecondaryAccountNo(account.getAccountNo());
				this.executeTransferSecondary((Transfer)action);
			}
		}
		
		if (action instanceof ViewBalance)
		{
			this.executeViewBalance();
		}
		
		if (action instanceof ViewStatement)
		{
			((ViewStatement)action).setAccountNo(account.getAccountNo());
			this.executeViewStatement((StatementRetrieval)action);
			
		}
	}
	
	if (source == btnSavingsAccount)
	{
		account = savingsAccount;
		
		if (action instanceof Withdrawal)
		{
			if (savingsAccount.getWithdrawalPending()) 		//To see if there is already a pending withdrawal
			{
				if (savingsAccount.getFundsAvailableDate().after(new Date()))		 //Returns true if the funds are not yet available
				{
					JOptionPane.showMessageDialog(null, "You already have a withdrawal pending on this account. The amount of " + savingsAccount.getPendingWithdrawalAmount() + " will be available on " + savingsAccount.getFundsAvailableDate());
				}
				else		 //There is a withdrawal pending and the funds are already available
				{
					if (JOptionPane.showInternalConfirmDialog(null, "You have R" + savingsAccount.getPendingWithdrawalAmount() + " available for withdrawal. Would you like to withdraw this amount now?", "Confirm Withdraw", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					{
						if (this.executeSavingsAccountWithdrawal((Withdrawal)action)) 		//Successful withdrawal - transaction sent to server
						{
							//Call DNRManager methods
							
						}
						
					}
					else 		//User chose not to draw funds now
					{
						JOptionPane.showMessageDialog(null, "You will be returned to the main menu");
						new ATMAccountHolderMainMenu(framePanel, accountHolder);
					}
				}
			}
			else		//There is no withdrawal pending
			{
				Withdrawal withdrawal = new Withdrawal(account.getAccountNo(), 0.0, true);
				new WithdrawCashSelectAmount(framePanel, accountHolder, withdrawal, true);
				System.out.println("Withdrawal started. Account: " + savingsAccount.toString());
			}
		}
		
		if (action instanceof Deposit)
		{
			((Deposit)action).setPrimAccountNo(currentAccount.getAccountNo());
			this.executeDeposit((Deposit)action);
		}
		
		if (action instanceof Transfer)
		{
			
			if ((((Transfer) action).getPrimAccountNo() == null) || (((Transfer) action).getPrimAccountNo() == ""))
			{
				((Transfer)action).setPrimAccountNo(account.getAccountNo());
				this.executeTransferPrimary((Transfer)action);
			}
			else
			{
				((Transfer)action).setSecondaryAccountNo(currentAccount.getAccountNo());
				this.executeTransferSecondary((Transfer)action);
			}
		}
		
		if (action instanceof ViewBalance)
		{
			this.executeViewBalance();
		}
		
		if (action instanceof ViewStatement)
		{
			((ViewStatement)action).setAccountNo(currentAccount.getAccountNo());
			this.executeViewStatement(new StatementRetrieval(account.getAccountNo()));
				
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

public boolean executeCurrentAccountWithdrawal(Withdrawal withdrawal)
{
	new WithdrawCashSelectAmount(framePanel, accountHolder, withdrawal, false);
	System.out.println("Withdrawal started. Account: " + account.toString());
	return true;
}

public boolean executeSavingsAccountWithdrawal (Withdrawal withdrawal)
{
	boolean withdrawalSuccesful = ATMApplication.serverComm.sendWithdrawal(withdrawal);
	
	if (withdrawalSuccesful)
	{
		System.out.println("Withdrawal successfully processed: " + withdrawal.toString());
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.DATE, 32);
		JOptionPane.showMessageDialog(null, "Request processed - your funds will be available on " + calender, "Request Processed", JOptionPane.INFORMATION_MESSAGE);
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

public boolean executeDeposit(Deposit deposit)
{
	new EnterAmount(framePanel, accountHolder, deposit, false);
	System.out.println("Deposit started. Account: " + account.toString());
	return true;
}

public boolean executeTransferPrimary (Transfer transfer)
{
	transfer.setPrimAccountNo(currentAccount.getAccountNo());
	new SelectAccount(framePanel, accountHolder, transfer, "transfer TO");
	System.out.println("Transfer phase one started. Account: " + account.toString());
	return true;
}

public boolean executeTransferSecondary (Transfer transfer)
{
	new WithdrawCashSelectAmount(framePanel, accountHolder, transfer, false);
	System.out.println("Transfer phase two started. Account: " + account.toString());
	return true;
}

public boolean executeViewBalance()
{
	new ViewShowAccountBalance(framePanel, accountHolder, account.getBalance());
	return true;
}

public boolean executeViewStatement(StatementRetrieval statementRetrieval)
{
	ArrayList<Transaction>statement = ATMApplication.serverComm.sendStatementRetrieval(statementRetrieval);
	new ViewShowAccountStatement(framePanel, accountHolder, statement);
	return true;
}



}
