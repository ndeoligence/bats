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

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.innotec.bats.general.Account;

public class ConfirmOpenAcc extends JDialog implements ActionListener
{

	private final JPanel contentPanel = new JPanel();
	private JLabel lblCloseAccount;
	private JButton btnOK;
	private Account account;
	private TellerHomePage tellerHomePage;
	private JPanel framePanel;

	/**
	 * Create the dialog.
	 */
	public ConfirmOpenAcc(JPanel framePanel, Account account)
	{
		this.framePanel = framePanel;
		this.account = account; 
		setResizable(false);
		setTitle("Confirm Opening Of Account");
		setBounds(100, 100, 448, 304);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaption);
		contentPanel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(new Color(70, 130, 180), 2)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			lblCloseAccount = new JLabel("Account Number:");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblCloseAccount, 97, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblCloseAccount, 37, SpringLayout.WEST, contentPanel);
			lblCloseAccount.setHorizontalAlignment(SwingConstants.LEFT);
			lblCloseAccount.setFont(new Font("Cambria", Font.BOLD, 22));
			contentPanel.add(lblCloseAccount);
		}
		{
			JLabel label = new JLabel(account.getAccountNo());
			sl_contentPanel.putConstraint(SpringLayout.NORTH, label, 99, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, label, 37, SpringLayout.EAST, lblCloseAccount);
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setFont(new Font("Cambria", Font.ITALIC, 18));
			contentPanel.add(label);
		}
		{
			JLabel lblOpenNewAccount = new JLabel("Open New Account!");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblOpenNewAccount, 31, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblOpenNewAccount, 93, SpringLayout.WEST, contentPanel);
			lblOpenNewAccount.setHorizontalAlignment(SwingConstants.LEFT);
			lblOpenNewAccount.setFont(new Font("Cambria", Font.BOLD, 27));
			contentPanel.add(lblOpenNewAccount);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				btnOK = new JButton("OK");
				btnOK.setFont(new Font("Cambria", Font.BOLD, 18));
				buttonPane.add(btnOK);
			}
		}

		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent acEvent)
	{
		Object source = acEvent.getSource();
		if(source == btnOK)
		{
			tellerHomePage = new TellerHomePage(framePanel);
		}
	}

}
