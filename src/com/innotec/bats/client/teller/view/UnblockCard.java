package com.innotec.bats.client.teller.view;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import com.innotec.bats.client.teller.control.BankTellerApplication;
import com.innotec.bats.general.AccountHolder;
import com.innotec.bats.general.CardReactivation;


public class UnblockCard extends JPanel implements ActionListener
{
	private JTextField textField;
	private JTextField textField_1;
	private JPanel framePanel;
	private JButton backButton, btnUnblock, btnCancel;
	private TellerHomePage tellerHomePage;
	private ConfirmExitDialog confirmExitDialog;
	private UnblockAccConfirmation unblockAccConfirmation;
	private JLabel lblBlocked;
	private AccountHolder accountHolder;
	private CardReactivation cardReactivation;

	public UnblockCard(JPanel framePanel, AccountHolder accountHolder)
	{
		this.accountHolder = accountHolder;
		framePanel.removeAll();
		this.framePanel = framePanel;
		setBackground(SystemColor.inactiveCaption);
		setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new EtchedBorder(EtchedBorder.LOWERED, new Color(244, 247, 252), new Color(153, 180, 209))));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(70, 130, 180), 2), "Teller/ Administrator", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(70, 130, 180)));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel label = new JLabel("New City Bank");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(SystemColor.textHighlight);
		label.setFont(new Font("Cambria", Font.BOLD, 46));
		label.setBackground(SystemColor.inactiveCaption);
		panel.add(label);
		
		JLabel lblUnblockAccount = new JLabel("Unblock Card:");
		lblUnblockAccount.setForeground(SystemColor.textHighlight);
		lblUnblockAccount.setFont(new Font("Cambria", Font.BOLD, 30));
		panel.add(lblUnblockAccount);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(70, 130, 180), 2), "Client Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(70, 130, 180)));
		panel_1.setBackground(SystemColor.inactiveCaption);
		add(panel_1, BorderLayout.CENTER);
		
		JLabel lblAccountHolder = new JLabel("Account Holder:");
		lblAccountHolder.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountHolder.setFont(new Font("Cambria", Font.BOLD, 38));
		
		textField = new JTextField();
		textField.setFont(new Font("Cambria", Font.PLAIN, 22));
		textField.setColumns(10);
		textField.setText(accountHolder.getName() + " " + accountHolder.getSurname());
		
		JLabel lblAccountNumber = new JLabel("Card Number:");
		lblAccountNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountNumber.setFont(new Font("Cambria", Font.BOLD, 38));
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Cambria", Font.PLAIN, 22));
		textField_1.setColumns(10);
		textField_1.setText(accountHolder.getCard().getCardNo());
		
		JLabel lblAccountStatus = new JLabel("Card Status:");
		lblAccountStatus.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountStatus.setFont(new Font("Cambria", Font.BOLD, 38));
		
		lblBlocked = new JLabel("");
		lblBlocked.setHorizontalAlignment(SwingConstants.LEFT);
		lblBlocked.setFont(new Font("Cambria", Font.ITALIC, 30));
		
		btnUnblock = new JButton("Unblock Card");
		btnUnblock.setFont(new Font("Cambria", Font.BOLD, 24));
		btnUnblock.addActionListener(this);
		
		btnCancel = new JButton("Exit");
		btnCancel.setFont(new Font("Cambria", Font.BOLD, 24));
		btnCancel.addActionListener(this);
		
		SpringLayout sl_panel_1 = new SpringLayout();
		sl_panel_1.putConstraint(SpringLayout.WEST, btnCancel, 262, SpringLayout.EAST, btnUnblock);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnCancel, -356, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnUnblock, -797, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblBlocked, 0, SpringLayout.WEST, textField_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, textField);
		sl_panel_1.putConstraint(SpringLayout.EAST, textField_1, -629, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnCancel, 517, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnCancel, -28, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblAccountNumber, 145, SpringLayout.SOUTH, lblAccountHolder);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnUnblock, 356, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnUnblock, -28, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, lblBlocked, 0, SpringLayout.SOUTH, lblAccountStatus);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblAccountStatus, 79, SpringLayout.SOUTH, lblAccountNumber);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, textField_1, 0, SpringLayout.SOUTH, lblAccountNumber);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, textField, 0, SpringLayout.SOUTH, lblAccountHolder);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblAccountStatus, 16, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblAccountNumber, 16, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, textField, 46, SpringLayout.EAST, lblAccountHolder);
		sl_panel_1.putConstraint(SpringLayout.EAST, textField, -629, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblAccountHolder, 26, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblAccountHolder, 16, SpringLayout.WEST, panel_1);
		panel_1.setLayout(sl_panel_1);
		panel_1.add(lblAccountHolder);
		panel_1.add(textField);
		panel_1.add(lblAccountNumber);
		panel_1.add(textField_1);
		panel_1.add(lblAccountStatus);
		panel_1.add(lblBlocked);
		panel_1.add(btnUnblock);
		panel_1.add(btnCancel);
		
	    backButton = new JButton("");
		backButton.setIcon(new ImageIcon("resources/ReturnIconWithoutBackground.png"));
		sl_panel_1.putConstraint(SpringLayout.WEST, backButton, 10, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, backButton, -10, SpringLayout.SOUTH, panel_1);
		backButton.addActionListener(this);
		panel_1.add(backButton);
		
		if(accountHolder.getCard().isActive())
		{
			lblBlocked.setText("Card Active");
		}
		else
		{
			lblBlocked.setText("Card Inactive");
		}
		
		framePanel.add(this);
		framePanel.validate();
		framePanel.repaint();
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent acEvent)
	{
		Object source = acEvent.getSource();
		if(source == backButton)
		{
			tellerHomePage = new TellerHomePage(framePanel);
		}
		if(source == btnCancel)
		{
			confirmExitDialog = new ConfirmExitDialog();
		}
		if(source == btnUnblock)
		{
			unblockAccConfirmation = new UnblockAccConfirmation();

			if(unblockAccConfirmation.okBtnPressed())
			{
				cardReactivation = new CardReactivation(accountHolder.getCard().getCardNo(), TellerHomePage.tellerID);
				BankTellerApplication.serverComm.sendCardReactivation(cardReactivation);
				if(accountHolder.getCard().isActive())
				{
					lblBlocked.setText("Card Active");		
				}
			}

		}
	}

}
