package com.innotec.bats.client.teller.view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
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
import javax.swing.JTextField;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import com.innotec.bats.client.teller.control.BankTellerApplication;
import com.innotec.bats.general.AccountHolder;
import com.innotec.bats.general.AccountHolderRetrievalByAccountNo;


public class AccountNumber_Teller extends JDialog implements ActionListener
{

	private final JPanel contentPanel = new JPanel();
	private JPanel framePanel = new JPanel();
	private JLabel lblEnterClientsAccount;
	private JLabel lblAccountNo;
	private JTextField textField;
	private JButton btnOk, button_1;
    private String accountNo;
    private AccountHolder accountHolder;
    private UnblockCard unblockCard;
    private AccountHolderRetrievalByAccountNo accountHolderRetrievalByAccountNo;
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			AccountNumber_Teller dialog = new AccountNumber_Teller();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AccountNumber_Teller()
	{
		setResizable(false);
		setTitle("New City Bank");
		setBounds(100, 100, 552, 375);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaption);
		contentPanel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(new Color(70, 130, 180), 2)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			lblEnterClientsAccount = new JLabel("Account Holder's Information!");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblEnterClientsAccount, 10, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblEnterClientsAccount, 77, SpringLayout.WEST, contentPanel);
			lblEnterClientsAccount.setHorizontalAlignment(SwingConstants.LEFT);
			lblEnterClientsAccount.setFont(new Font("Cambria", Font.BOLD, 26));
			contentPanel.add(lblEnterClientsAccount);
		}
		{
			lblAccountNo = new JLabel("Account Number:");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblAccountNo, 89, SpringLayout.SOUTH, lblEnterClientsAccount);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblAccountNo, 10, SpringLayout.WEST, contentPanel);
			lblAccountNo.setHorizontalAlignment(SwingConstants.LEFT);
			lblAccountNo.setFont(new Font("Cambria", Font.BOLD, 20));
			contentPanel.add(lblAccountNo);
		}
		
		textField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, textField, 89, SpringLayout.SOUTH, lblEnterClientsAccount);
		sl_contentPanel.putConstraint(SpringLayout.WEST, textField, 200, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, textField, -33, SpringLayout.EAST, contentPanel);
		textField.setFont(new Font("Cambria", Font.PLAIN, 14));
		textField.setColumns(10);
		contentPanel.add(textField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			btnOk = new JButton("  OK  ");
			btnOk.setFont(new Font("Cambria", Font.BOLD, 18));
			btnOk.addActionListener(this);
			buttonPane.add(btnOk);
			
			button_1 = new JButton("Cancel");
			button_1.setFont(new Font("Cambria", Font.BOLD, 18));
			button_1.addActionListener(this);
			buttonPane.add(button_1);
		}
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent acEvent)
	{
		Object source = acEvent.getSource();
		if(source == btnOk)
		{
			accountNo = textField.getText();
			accountHolderRetrievalByAccountNo = new AccountHolderRetrievalByAccountNo(accountNo);
			accountHolder = BankTellerApplication.serverComm.sendAccountHolderRetrievalByAccountNo(accountHolderRetrievalByAccountNo);
			if(accountHolder != null)
			{
				unblockCard = new UnblockCard(framePanel, accountHolder);
			}
			
			JOptionPane.showMessageDialog(null, "Account Holder error! /nIs account number correct?", "Account Holder does not exist", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		}
		if(source == button_1)
		{
			this.dispose();
		}
	}
	
	public AccountHolder getAccountHolder()
	{
		return accountHolder;
	}
}
