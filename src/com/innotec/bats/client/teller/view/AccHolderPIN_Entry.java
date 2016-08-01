package com.innotec.bats.client.teller.view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.SystemColor;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

import com.innotec.bats.client.atm.control.ATM_ServerComm;
import com.innotec.bats.client.teller.control.BankTellerApplication;
import com.innotec.bats.general.Account;
import com.innotec.bats.general.AccountCreation;
import com.innotec.bats.general.AccountHolder;
import com.innotec.bats.general.AccountHolderCreation;
import com.innotec.bats.general.Card;


public class AccHolderPIN_Entry extends JDialog implements ActionListener
{

	private final JPanel contentPanel = new JPanel();
	private JLabel lblEnterPin;
	private JLabel lblConfirmPin;
	private JButton btnSubmit, btnCancel;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private PINnotMatching piNnotMatchingDialog;
	private JPanel framePanel;
	private AccountHolder accountHolder;
	private Card card;
	private TellerHomePage tellerHomePage;
	private AccountHolderCreation accountHolderCreation;
	private AccountCreation accountCreation;
	private Account account;
	private boolean tf;

//	public static void main(String[] args)
//	{
//		try
//		{
//			AccHolderPIN_Entry dialog = new AccHolderPIN_Entry();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AccHolderPIN_Entry(JPanel framePanel, AccountHolder accountHolder)
	{
		framePanel.removeAll();
		this.framePanel = framePanel;
		this.accountHolder = accountHolder;
		setResizable(false);
		setTitle("Please Enter PIN");
		setBounds(100, 100, 470, 355);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaption);
		contentPanel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(new Color(70, 130, 180), 2)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			lblEnterPin = new JLabel("Enter PIN:");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblEnterPin, 34, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblEnterPin, 10, SpringLayout.WEST, contentPanel);
			lblEnterPin.setHorizontalAlignment(SwingConstants.LEFT);
			lblEnterPin.setFont(new Font("Cambria", Font.BOLD, 25));
			contentPanel.add(lblEnterPin);
		}
		{
			lblConfirmPin = new JLabel("Confirm PIN:");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblConfirmPin, 43, SpringLayout.SOUTH, lblEnterPin);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblConfirmPin, 10, SpringLayout.WEST, contentPanel);
			lblConfirmPin.setHorizontalAlignment(SwingConstants.LEFT);
			lblConfirmPin.setFont(new Font("Cambria", Font.BOLD, 25));
			contentPanel.add(lblConfirmPin);
		}
		
		passwordField = new JPasswordField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, passwordField, 5, SpringLayout.NORTH, lblEnterPin);
		sl_contentPanel.putConstraint(SpringLayout.WEST, passwordField, 53, SpringLayout.EAST, lblEnterPin);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, passwordField, 25, SpringLayout.NORTH, lblEnterPin);
		sl_contentPanel.putConstraint(SpringLayout.EAST, passwordField, 183, SpringLayout.EAST, lblEnterPin);
		contentPanel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, passwordField_1, 5, SpringLayout.NORTH, lblConfirmPin);
		sl_contentPanel.putConstraint(SpringLayout.WEST, passwordField_1, -130, SpringLayout.EAST, passwordField);
		sl_contentPanel.putConstraint(SpringLayout.EAST, passwordField_1, 0, SpringLayout.EAST, passwordField);
		contentPanel.add(passwordField_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			buttonPane.setForeground(SystemColor.inactiveCaption);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			btnSubmit = new JButton("Submit");
			btnSubmit.setFont(new Font("Cambria", Font.BOLD, 16));
			btnSubmit.addActionListener(this);
			buttonPane.add(btnSubmit);
			
			btnCancel = new JButton("Cancel");
			btnCancel.setFont(new Font("Cambria", Font.BOLD, 16));
			btnCancel.addActionListener(this);
			buttonPane.add(btnCancel);
		}
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public boolean setAccount(Account account)
	{
		this.account = account;
		if(!(account.equals(null)))
		{
			tf = true;
		}

		if(account.equals(null))
		{
			tf =false;
		}
		return tf;
	}
	
	@Override
	public void actionPerformed(ActionEvent acEvent)
	{
		Object source = acEvent.getSource();
		if(source == btnSubmit)
		{
			if(String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordField_1.getPassword())))
			{
				card = new Card("", String.valueOf(passwordField.getPassword()), true);
				accountHolder.addCard(card);
				accountHolderCreation = new AccountHolderCreation(accountHolder, TellerHomePage.tellerID);
				BankTellerApplication.serverComm.sendAccountHolderCreation(accountHolderCreation);
				
				if(!(account.equals(null)))
				{
					accountCreation = new AccountCreation(TellerHomePage.tellerID, account, accountHolder.getAccountHolderID());
					BankTellerApplication.serverComm.sendAccountCreation(accountCreation);
				}
				tellerHomePage = new TellerHomePage(framePanel);
			}
			else
			{
				piNnotMatchingDialog = new PINnotMatching();
				passwordField.setText(null);
				passwordField_1.setText(null);
			}
		}
		if(source == btnCancel)
		{
			this.dispose();
		}
	}
}
