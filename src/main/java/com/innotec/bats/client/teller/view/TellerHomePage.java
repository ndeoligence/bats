package com.innotec.bats.client.teller.view;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.BoxLayout;

import java.awt.BorderLayout;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Component;


public class TellerHomePage extends JPanel implements ActionListener
{
	private JButton btnNewAcc, btnAddNewAcc, btnCloseAcc, btnExit, btnUnblockAcc;
	private JPanel framePanel;
	private OpenNewAccount openNewAccountPanel;
	private AccHolderIDno_Teller idDialog;
	private AccountNumber_Teller accNoDialog, accNoDialog2;
	private CloseAccount_Teller closeAccountPanel;
	private UnblockCard unblockCardPanel;
	private ConfirmExitDialog confirmExitDialog;
	private OpenNewAccountForExistingAccountHolder newAccountForExistingAccountHolder;
	public static final String tellerID = "chiroptera13801";
	
	public TellerHomePage(JPanel framePanel)
	{
		framePanel.removeAll();
		this.framePanel = framePanel;
		setBackground(SystemColor.inactiveCaption);
		setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new EtchedBorder(EtchedBorder.LOWERED, new Color(244, 247, 252), new Color(153, 180, 209))));
		setLayout(null);
		
		JPanel panel = new JPanel();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		panel.setBounds(7, 7, 1349, 138);
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(70, 130, 180), 2), "Branch Teller", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(70, 130, 180)));
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("New City Bank");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 85));
		lblNewLabel.setBackground(SystemColor.inactiveCaption);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(7, 145, 1349, 594);
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBorder(null);
		add(panel_1);
		
	    btnNewAcc = new JButton("Create New Acc");
		btnNewAcc.addActionListener(this);
		btnNewAcc.setFont(new Font("Cambria", Font.BOLD, 30));
		
	    btnAddNewAcc = new JButton("Add New Acc");
		btnAddNewAcc.addActionListener(this);
		btnAddNewAcc.setFont(new Font("Cambria", Font.BOLD, 30));
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(this);
		btnExit.setFont(new Font("Cambria", Font.BOLD, 30));
		
		JLabel lblNewLabel_1 = new JLabel("Establish New Account Holder:");
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD, 34));
		
		JLabel lblNewLabel_2 = new JLabel("Existing Account Holder:");
		lblNewLabel_2.setFont(new Font("Cambria", Font.BOLD, 34));
		
		JLabel label = new JLabel("Existing Account Holder:");
		label.setFont(new Font("Cambria", Font.BOLD, 34));
		
		btnCloseAcc = new JButton("Close Acc");
		btnCloseAcc.addActionListener(this);
		btnCloseAcc.setFont(new Font("Cambria", Font.BOLD, 30));
		
		SpringLayout sl_panel_1 = new SpringLayout();
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnCloseAcc, 243, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnCloseAcc, 719, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnCloseAcc, 288, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnCloseAcc, 0, SpringLayout.EAST, btnNewAcc);
		sl_panel_1.putConstraint(SpringLayout.NORTH, label, 247, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnAddNewAcc, 168, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnAddNewAcc, 719, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnAddNewAcc, 213, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnAddNewAcc, 0, SpringLayout.EAST, btnNewAcc);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 172, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel_2, 10, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 94, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel_1, 10, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnNewAcc, 0, SpringLayout.NORTH, lblNewLabel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnNewAcc, 233, SpringLayout.EAST, lblNewLabel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnExit, 577, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnExit, -70, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnExit, 771, SpringLayout.WEST, panel_1);
		
		panel_1.setLayout(sl_panel_1);

		panel_1.add(lblNewLabel_1);
		panel_1.add(btnNewAcc);
		panel_1.add(lblNewLabel_2);
		panel_1.add(btnAddNewAcc);
		panel_1.add(label);
		panel_1.add(btnCloseAcc);
		panel_1.add(btnExit);
		
		JLabel label_1 = new JLabel("Existing Account Holder:");
		sl_panel_1.putConstraint(SpringLayout.WEST, label_1, 10, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_1, 31, SpringLayout.SOUTH, label);
		label_1.setFont(new Font("Cambria", Font.BOLD, 34));
		panel_1.add(label_1);
		
		btnUnblockAcc = new JButton("Unblock Card");
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnUnblockAcc, -45, SpringLayout.SOUTH, label_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnUnblockAcc, -630, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnUnblockAcc, 0, SpringLayout.SOUTH, label_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnUnblockAcc, 0, SpringLayout.EAST, btnCloseAcc);
		btnUnblockAcc.setFont(new Font("Cambria", Font.BOLD, 30));
		btnUnblockAcc.addActionListener(this);
		panel_1.add(btnUnblockAcc);

		framePanel.add(this);
		framePanel.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent acEvent)
	{
		Object source = acEvent.getSource();
		if(source == btnNewAcc)
		{
			framePanel.removeAll();
			framePanel.validate();

			openNewAccountPanel = new OpenNewAccount(framePanel);
		}
		if(source == btnAddNewAcc)
		{
			idDialog = new AccHolderIDno_Teller();
			//if(idDialog.isAnAccHolder() == true)
			//{
				framePanel.removeAll();
				framePanel.validate();

				newAccountForExistingAccountHolder = new OpenNewAccountForExistingAccountHolder(framePanel);
			//}
		}
		if(source == btnCloseAcc)
		{
			accNoDialog = new AccountNumber_Teller();
//			if()
//			{
				framePanel.removeAll();
				framePanel.validate();

				closeAccountPanel = new CloseAccount_Teller(framePanel);
//			}
		}
		if(source == btnUnblockAcc)
		{
			accNoDialog2 = new AccountNumber_Teller();
//			if(accNoDialog.getAccountHolder() != null)
//			{
				framePanel.removeAll();
				framePanel.validate();
				
				unblockCardPanel = new UnblockCard(framePanel);
//			}
		}
		if(source == btnExit)
		{
			confirmExitDialog = new ConfirmExitDialog();
		}
	}
}
