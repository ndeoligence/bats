package com.innotec.bats.client.teller.view;
import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import com.innotec.bats.general.Account;
import com.innotec.bats.general.AccountHolder;
import com.innotec.bats.general.CurrentAccount;
import com.innotec.bats.general.SavingsAccount;


public class OpenNewAccount extends JPanel implements ActionListener
{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private ButtonGroup bg, bg1;
	private JPanel framePanel;
	private JButton btnOpenAccount, btnCancel, btnBackBtn;
	private ConfirmExitDialog confirmExitDialog;
	private AccHolderPIN_Entry accHolderPIN_Entry;
	private JRadioButton rdbtnNewRadioButton, rdbtnWithdrawalMax, rdbtnDefaultTransferAmount, rdbtnTransferMaxR;
	private TellerHomePage tellerHomePage;
	private AccountHolder accountHolder;
	private Account account;
	private SavingsAccount savingsAccount;
	private CurrentAccount currentAccount;
	private String name, surname, idNo, address, contactNo;
	private JCheckBox chckbxCurrentAccount, chckbxSavingsAccount, chckbxCreditCard;
	private String accountNo = "";
	private boolean active = true; 
	private double maxWithdrawalPerDay, maxTransferPerDay;
	private final double balanceCurrent = 100, balanceSavings = 1000;

	public OpenNewAccount(JPanel framePanel)
	{
		framePanel.removeAll();
		this.framePanel = framePanel;
		setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new EtchedBorder(EtchedBorder.LOWERED, new Color(244, 247, 252), new Color(153, 180, 209))));
		setBackground(SystemColor.inactiveCaption);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		panel.setBorder(new TitledBorder(new LineBorder(new Color(70, 130, 180), 2), "Branch Teller", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(70, 130, 180)));
		panel.setBackground(SystemColor.inactiveCaption);
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label = new JLabel("New City Bank");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(SystemColor.textHighlight);
		label.setFont(new Font("Cambria", Font.BOLD, 46));
		label.setBackground(SystemColor.inactiveCaption);
		panel.add(label);
		
		JLabel lblOpenNewAccount = new JLabel("Open New Account:");
		lblOpenNewAccount.setForeground(SystemColor.textHighlight);
		lblOpenNewAccount.setFont(new Font("Cambria", Font.BOLD, 30));
		panel.add(lblOpenNewAccount);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(SystemColor.inactiveCaption);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(70, 130, 180), 2), "Client Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(70, 130, 180)));
		panel_1.setBackground(SystemColor.inactiveCaption);
		add(panel_1, BorderLayout.CENTER);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Cambria", Font.BOLD, 34));
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		
		textField = new JTextField();
		textField.setFont(new Font("Cambria", Font.PLAIN, 20));
		textField.setColumns(10);
		name = textField.getText();
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setHorizontalAlignment(SwingConstants.LEFT);
		lblSurname.setFont(new Font("Cambria", Font.BOLD, 34));
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Cambria", Font.PLAIN, 20));
		textField_1.setColumns(10);
		surname = textField_1.getText();
		
		JLabel lblIdNo = new JLabel("ID No:");
		lblIdNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdNo.setFont(new Font("Cambria", Font.BOLD, 34));
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Cambria", Font.PLAIN, 20));
		textField_2.setColumns(10);
		idNo = textField_2.getText();
		
		JLabel lblCellNo = new JLabel("Cell No:");
		lblCellNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCellNo.setFont(new Font("Cambria", Font.BOLD, 34));
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Cambria", Font.PLAIN, 20));
		textField_3.setColumns(10);
		contactNo = textField_3.getText();
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress.setFont(new Font("Cambria", Font.BOLD, 30));
		
		rdbtnNewRadioButton = new JRadioButton("Default Daily Withdrawal Amount (R1 000.00)");
		rdbtnNewRadioButton.setBackground(SystemColor.inactiveCaption);
		rdbtnNewRadioButton.setFont(new Font("Cambria", Font.BOLD, 24));
		rdbtnNewRadioButton.addActionListener(this);
		rdbtnNewRadioButton.setSelected(true);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Cambria", Font.PLAIN, 20));
		address = textArea.getText();
		
		rdbtnWithdrawalMax = new JRadioButton("Withdrawal Max  R:");
		rdbtnWithdrawalMax.setFont(new Font("Cambria", Font.BOLD, 24));
		rdbtnWithdrawalMax.setBackground(SystemColor.inactiveCaption);
		rdbtnWithdrawalMax.addActionListener(this);
		
		bg = new ButtonGroup();
		bg.add(rdbtnNewRadioButton);
		bg.add(rdbtnWithdrawalMax);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Cambria", Font.PLAIN, 20));
		textField_4.setColumns(10);
		textField_4.setEnabled(false);
		
		rdbtnDefaultTransferAmount = new JRadioButton("Default Daily Transfer Amount (R1 000.00)/ Acc");
		rdbtnDefaultTransferAmount.setFont(new Font("Cambria", Font.BOLD, 24));
		rdbtnDefaultTransferAmount.setBackground(SystemColor.inactiveCaption);
		rdbtnDefaultTransferAmount.addActionListener(this);
		rdbtnDefaultTransferAmount.setSelected(true);
		
		rdbtnTransferMaxR = new JRadioButton("Transfer Max  R:");
		rdbtnTransferMaxR.setFont(new Font("Cambria", Font.BOLD, 24));
		rdbtnTransferMaxR.setBackground(SystemColor.inactiveCaption);
		rdbtnTransferMaxR.addActionListener(this);
		
		bg1 = new ButtonGroup();
		bg1.add(rdbtnDefaultTransferAmount);
		bg1.add(rdbtnTransferMaxR);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Cambria", Font.PLAIN, 20));
		textField_5.setColumns(10);
		textField_5.setEnabled(false);
		
		chckbxCurrentAccount = new JCheckBox("Current Account");
		chckbxCurrentAccount.setBackground(SystemColor.inactiveCaption);
		chckbxCurrentAccount.setFont(new Font("Cambria", Font.BOLD, 24));
		chckbxCurrentAccount.addActionListener(this);
		
		chckbxSavingsAccount = new JCheckBox("Credit Card");
		chckbxSavingsAccount.setFont(new Font("Cambria", Font.BOLD, 24));
		chckbxSavingsAccount.setBackground(SystemColor.inactiveCaption);
		chckbxSavingsAccount.addActionListener(this);
		
		chckbxCreditCard = new JCheckBox("Savings Account");
		chckbxCreditCard.setFont(new Font("Cambria", Font.BOLD, 24));
		chckbxCreditCard.setBackground(SystemColor.inactiveCaption);
		chckbxCreditCard.addActionListener(this);
		
		JLabel lblAccountType = new JLabel("Account Type:");
		lblAccountType.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountType.setFont(new Font("Cambria", Font.BOLD, 30));
		
		btnOpenAccount = new JButton("Open Account");
		btnOpenAccount.setFont(new Font("Cambria", Font.BOLD, 26));
		btnOpenAccount.addActionListener(this);
		
		btnCancel = new JButton("Exit");
		btnCancel.setFont(new Font("Cambria", Font.BOLD, 26));
		btnCancel.addActionListener(this);
		
		SpringLayout sl_panel_1 = new SpringLayout();
		sl_panel_1.putConstraint(SpringLayout.NORTH, textField_1, 20, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, textField_1, -43, SpringLayout.NORTH, textField_3);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblSurname, -44, SpringLayout.WEST, textField_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, textField_3, -459, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, textField_2, -23, SpringLayout.NORTH, textArea);
		sl_panel_1.putConstraint(SpringLayout.WEST, textField_3, 78, SpringLayout.EAST, lblCellNo);
		sl_panel_1.putConstraint(SpringLayout.EAST, textField_3, -245, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, textArea, 27, SpringLayout.EAST, lblAddress);
		sl_panel_1.putConstraint(SpringLayout.EAST, textArea, -46, SpringLayout.WEST, rdbtnNewRadioButton);
		sl_panel_1.putConstraint(SpringLayout.EAST, textField_1, -245, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, textField_2, 510, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblAccountType, 129, SpringLayout.SOUTH, textArea);
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnNewRadioButton, 17, SpringLayout.SOUTH, lblCellNo);
		sl_panel_1.putConstraint(SpringLayout.NORTH, textField_2, 43, SpringLayout.SOUTH, textField);
		sl_panel_1.putConstraint(SpringLayout.NORTH, textArea, 0, SpringLayout.NORTH, lblAddress);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, textArea, 246, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnWithdrawalMax, 3, SpringLayout.SOUTH, rdbtnNewRadioButton);
		sl_panel_1.putConstraint(SpringLayout.NORTH, rdbtnTransferMaxR, 281, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnDefaultTransferAmount, 0, SpringLayout.WEST, lblSurname);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, rdbtnDefaultTransferAmount, -6, SpringLayout.NORTH, rdbtnTransferMaxR);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblAddress, 146, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, textField_5, 817, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, textField_4, 817, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, rdbtnTransferMaxR, -56, SpringLayout.WEST, textField_5);
		sl_panel_1.putConstraint(SpringLayout.EAST, rdbtnWithdrawalMax, -20, SpringLayout.WEST, textField_4);
		sl_panel_1.putConstraint(SpringLayout.NORTH, textField_5, 3, SpringLayout.NORTH, rdbtnTransferMaxR);
		sl_panel_1.putConstraint(SpringLayout.NORTH, textField_4, 3, SpringLayout.NORTH, rdbtnWithdrawalMax);
		sl_panel_1.putConstraint(SpringLayout.WEST, textField_1, 750, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, rdbtnNewRadioButton, 0, SpringLayout.WEST, lblCellNo);
		sl_panel_1.putConstraint(SpringLayout.WEST, textField, 50, SpringLayout.EAST, lblName);
		sl_panel_1.putConstraint(SpringLayout.EAST, textField, -46, SpringLayout.WEST, lblSurname);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnCancel, 601, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnCancel, -602, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, chckbxSavingsAccount, 0, SpringLayout.NORTH, lblAccountType);
		sl_panel_1.putConstraint(SpringLayout.NORTH, chckbxCreditCard, 0, SpringLayout.NORTH, lblAccountType);
		sl_panel_1.putConstraint(SpringLayout.NORTH, chckbxCurrentAccount, 0, SpringLayout.NORTH, lblAccountType);
		sl_panel_1.putConstraint(SpringLayout.WEST, chckbxSavingsAccount, 14, SpringLayout.EAST, chckbxCreditCard);
		sl_panel_1.putConstraint(SpringLayout.WEST, chckbxCreditCard, 26, SpringLayout.EAST, chckbxCurrentAccount);
		sl_panel_1.putConstraint(SpringLayout.EAST, chckbxCreditCard, -22, SpringLayout.EAST, lblSurname);
		sl_panel_1.putConstraint(SpringLayout.WEST, chckbxCurrentAccount, 31, SpringLayout.EAST, lblAccountType);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblAccountType, 0, SpringLayout.WEST, lblName);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnOpenAccount, 569, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnOpenAccount, -27, SpringLayout.NORTH, btnCancel);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnCancel, -24, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, textField_3, 93, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblCellNo, 32, SpringLayout.SOUTH, lblSurname);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblSurname, 15, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, textField, 20, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblName, 0, SpringLayout.WEST, lblIdNo);
		sl_panel_1.putConstraint(SpringLayout.WEST, textField_2, 0, SpringLayout.WEST, textField);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblCellNo, 0, SpringLayout.WEST, lblSurname);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblName, 15, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblAddress, 16, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblIdNo, 88, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblIdNo, 16, SpringLayout.WEST, panel_1);
		
		panel_1.setLayout(sl_panel_1);
		panel_1.add(lblName);
		panel_1.add(textField);
		panel_1.add(lblSurname);
		panel_1.add(textField_1);
		panel_1.add(lblIdNo);
		panel_1.add(textField_2);
		panel_1.add(lblCellNo);
		panel_1.add(textField_3);
		panel_1.add(lblAddress);
		panel_1.add(textArea);
		panel_1.add(rdbtnNewRadioButton);
		panel_1.add(rdbtnWithdrawalMax);
		panel_1.add(textField_4);
		panel_1.add(rdbtnDefaultTransferAmount);
		panel_1.add(rdbtnTransferMaxR);
		panel_1.add(textField_5);
		panel_1.add(lblAccountType);
		panel_1.add(chckbxCurrentAccount);
		panel_1.add(chckbxCreditCard);
		panel_1.add(chckbxSavingsAccount);
		panel_1.add(btnOpenAccount);
		panel_1.add(btnCancel);
		
		btnBackBtn = new JButton("");
		btnBackBtn.setIcon(new ImageIcon("resources/ReturnIconWithoutBackground.png"));
		sl_panel_1.putConstraint(SpringLayout.WEST, btnBackBtn, 0, SpringLayout.WEST, lblName);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnBackBtn, 0, SpringLayout.SOUTH, btnCancel);
		btnBackBtn.addActionListener(this);
		panel_1.add(btnBackBtn);
		
		framePanel.add(this);
		framePanel.validate();
		framePanel.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent acEvent)
	{
		Object source = acEvent.getSource();
		if(source == btnCancel)
		{
			confirmExitDialog = new ConfirmExitDialog();
		}
		if(source == btnOpenAccount)
		{
			accountHolder = new AccountHolder(name, surname, idNo, address, contactNo);
			if(rdbtnNewRadioButton.isSelected())
			{
				maxWithdrawalPerDay = 1000.00;
			}
			if(rdbtnWithdrawalMax.isSelected())
			{
				maxWithdrawalPerDay = Double.parseDouble(textField_4.getText());
			}
			if(rdbtnDefaultTransferAmount.isSelected())
			{
				maxTransferPerDay = 1000.00;
			}
			if(rdbtnTransferMaxR.isSelected())
			{
				maxTransferPerDay = Double.parseDouble(textField_5.getText());
			}
			if(chckbxCurrentAccount.isSelected())
			{
				Account currentAccount = new CurrentAccount(accountNo, balanceCurrent, active, maxWithdrawalPerDay, maxTransferPerDay);
				accountHolder.addAccount(currentAccount);
			}
			accHolderPIN_Entry = new AccHolderPIN_Entry(framePanel, accountHolder);
		}
		if(rdbtnNewRadioButton.isSelected())
		{
			textField_4.setEnabled(false);
		}
		if(rdbtnWithdrawalMax.isSelected())
		{
			textField_4.setEnabled(true);
		}
		if(rdbtnDefaultTransferAmount.isSelected())
		{
			textField_5.setEnabled(false);
		}
		if(rdbtnTransferMaxR.isSelected())
		{
			textField_5.setEnabled(true);
		}
		if(source == btnBackBtn)
		{
			tellerHomePage = new TellerHomePage(framePanel);
		}
	}
}
