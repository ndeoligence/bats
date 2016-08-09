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

import javax.swing.SpringLayout;
import javax.swing.JLabel;

import com.innotec.bats.general.Account;
import com.innotec.bats.general.AccountCreation;
import com.innotec.bats.general.AccountHolder;
import com.innotec.bats.general.CurrentAccount;
import com.innotec.bats.general.SavingsAccount;

public class NewAccConfirmation extends JDialog implements ActionListener
{

	private final JPanel contentPanel = new JPanel();
	private JPanel framePanel;
	private JButton okButton;
	private TellerHomePage tellerHomePage;
	private JLabel lblAccType, lblaccNo0000, lblCardNo0000;
	private Account account;
	private AccountHolder accountHolder;
	private AccountCreation accountCreation;
	private boolean tf;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args)
//	{
//		try
//		{
//			NewAccConfirmation dialog = new NewAccConfirmation();
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
	public NewAccConfirmation(AccountHolder accountHolder, Account account)
	{
		this.accountHolder = accountHolder;
		this.account = account;
//		framePanel.removeAll();
//		this.framePanel = framePanel;
		setTitle("New Account");
		setResizable(false);
		setBounds(100, 100, 488, 325);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaption);
		contentPanel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(new Color(70, 130, 180), 2)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		JLabel lblNewLabel = new JLabel("New Account:");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblNewLabel, 41, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblNewLabel, 40, SpringLayout.WEST, contentPanel);
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 22));
		contentPanel.add(lblNewLabel);
		
		JLabel lblAccountNo = new JLabel("Account No:");
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblAccountNo, 40, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lblAccountNo, -108, SpringLayout.SOUTH, contentPanel);
		lblAccountNo.setFont(new Font("Cambria", Font.BOLD, 22));
		contentPanel.add(lblAccountNo);
		
		lblAccType = new JLabel("");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblAccType, 0, SpringLayout.NORTH, lblNewLabel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblAccType, 44, SpringLayout.EAST, lblNewLabel);
		lblAccType.setFont(new Font("Cambria", Font.ITALIC, 22));
		contentPanel.add(lblAccType);
		
		if(account instanceof SavingsAccount)
		{
			lblAccType.setText("Savings");
		}
		if(account instanceof CurrentAccount)
		{
			lblAccType.setText("Current");
		}
		
		lblaccNo0000 = new JLabel("000000000000");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblaccNo0000, 0, SpringLayout.NORTH, lblAccountNo);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblaccNo0000, 61, SpringLayout.EAST, lblAccountNo);
		lblaccNo0000.setFont(new Font("Cambria", Font.ITALIC, 22));
		contentPanel.add(lblaccNo0000);
		lblaccNo0000.setText(account.getAccountNo());
		
		JLabel lblCardNo = new JLabel("Card No:");
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblCardNo, 40, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lblCardNo, -41, SpringLayout.SOUTH, contentPanel);
		lblCardNo.setFont(new Font("Cambria", Font.BOLD, 22));
		contentPanel.add(lblCardNo);
		
		lblCardNo0000 = new JLabel("000000000000");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblCardNo0000, 0, SpringLayout.NORTH, lblCardNo);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblCardNo0000, 0, SpringLayout.WEST, lblaccNo0000);
		lblCardNo0000.setFont(new Font("Cambria", Font.ITALIC, 22));
		lblCardNo.setText(accountHolder.getCard().getCardNo());
		
		contentPanel.add(lblCardNo0000);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				okButton = new JButton("OK");
				okButton.setFont(new Font("Cambria", Font.BOLD, 18));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(this);
				getRootPane().setDefaultButton(okButton);
			}
		}
//		framePanel.add(this);
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
		if(source == okButton)
		{
			tellerHomePage = new TellerHomePage(framePanel);
		}
	}
}
