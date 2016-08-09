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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import com.innotec.bats.client.teller.control.BankTellerApplication;
import com.innotec.bats.general.AccountClosure;
import com.innotec.bats.general.AccountHolder;

public class ConfirmAccClosure_WithPIN extends JDialog implements ActionListener
{

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JButton btnYes, btnNo;
	private JLabel label;
	private JLabel label_1;
	private AccountHolder accountHolder;
	private AccountClosure accountClosure;
	private boolean tf;


	/**
	 * Create the dialog.
	 */
	public ConfirmAccClosure_WithPIN(AccountHolder accountHolder)
	{
		this.accountHolder = accountHolder;
		setTitle("Close Account!");
		setResizable(false);
		setBounds(100, 100, 557, 385);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaption);
		contentPanel.setBorder(new CompoundBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(new Color(70, 130, 180), 2)), null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		JLabel lblPin = new JLabel("PIN");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblPin, 135, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblPin, 177, SpringLayout.WEST, contentPanel);
		lblPin.setFont(new Font("Cambria", Font.BOLD, 28));
		contentPanel.add(lblPin);
		
		passwordField = new JPasswordField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, passwordField, 142, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, passwordField, 6, SpringLayout.EAST, lblPin);
		sl_contentPanel.putConstraint(SpringLayout.EAST, passwordField, -192, SpringLayout.EAST, contentPanel);
		contentPanel.add(passwordField);
		
		label = new JLabel("Close Account:");
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, label, -45, SpringLayout.NORTH, lblPin);
		sl_contentPanel.putConstraint(SpringLayout.EAST, label, -295, SpringLayout.EAST, contentPanel);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Cambria", Font.BOLD, 27));
		contentPanel.add(label);
		
		label_1 = new JLabel("00000000000000");
		sl_contentPanel.putConstraint(SpringLayout.WEST, label_1, 63, SpringLayout.EAST, label);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, label_1, -55, SpringLayout.NORTH, passwordField);
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Cambria", Font.ITALIC, 22));
		contentPanel.add(label_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				btnYes = new JButton("YES");
				btnYes.setFont(new Font("Cambria", Font.BOLD, 18));
				btnYes.setActionCommand("OK");
				buttonPane.add(btnYes);
				btnYes.addActionListener(this);
				getRootPane().setDefaultButton(btnYes);
			}
			{
				btnNo = new JButton(" NO ");
				btnNo.setFont(new Font("Cambria", Font.BOLD, 18));
				btnNo.setActionCommand("Cancel");
				btnNo.addActionListener(this);
				buttonPane.add(btnNo);
			}
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent acEvent)
	{
		Object source = acEvent.getSource();
		if(source == btnYes)
		{
			if(String.valueOf(passwordField.getPassword()).equals(accountHolder.getCard().getPinNo()))
			{
				accountClosure = new AccountClosure(BankTellerApplication.tellerID, AccountNumber_Teller.ACCOUNTNO);
				tf = BankTellerApplication.serverComm.sendAccountClosure(accountClosure);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "PIN is incorrect! /n Please re-enter PIN",
						"PIN INCORRECT", JOptionPane.INFORMATION_MESSAGE);			
			}
			this.dispose();
		}
		if(source == btnNo)
		{
			this.dispose();
		}
	}
	
	public boolean accountClosed()
	{
		return tf;
	}
}
