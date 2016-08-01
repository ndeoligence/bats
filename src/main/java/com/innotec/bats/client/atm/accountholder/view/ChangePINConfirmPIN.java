package com.innotec.bats.client.atm.accountholder.view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.*;

import com.innotec.bats.client.atm.accountholder.control.ATMApplication;
import com.innotec.bats.general.AccountHolder;
import com.innotec.bats.general.PINChange;


public class ChangePINConfirmPIN extends JPanel implements ActionListener
{
	private JPanel framePanel;
	private JButton btnOK, btnHelp, btnCancel;
	private PINChange pinChange;
	private AccountHolder accountHolder;
	private JPasswordField passwordField;
/**
 * Create the panel.
 */
public ChangePINConfirmPIN (JPanel framePanel, AccountHolder accountHolder, PINChange pinChange)
{
	this.framePanel = framePanel;
	framePanel.removeAll();
	
	this.pinChange = pinChange;
	this.accountHolder = accountHolder;
	
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
	
	btnOK = new JButton("OK");
	sl_panel_2.putConstraint(SpringLayout.WEST, btnOK, 236, SpringLayout.WEST, panel_2);
	btnOK.setIcon(new ImageIcon("/YesIcon.jpg"));
	btnOK.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnOK);
	btnOK.addActionListener(this);
	
	btnHelp = new JButton("Help");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnHelp, 42, SpringLayout.SOUTH, btnOK);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnHelp, 20, SpringLayout.WEST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnHelp, -12, SpringLayout.SOUTH, panel_2);
	btnHelp.setIcon(new ImageIcon("resources/HelpIcon.jpg"));
	btnHelp.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnHelp);
	btnHelp.addActionListener(this);
	
	btnCancel = new JButton("Cancel");
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnOK, -40, SpringLayout.NORTH, btnCancel);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnHelp, -23, SpringLayout.WEST, btnCancel);
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnCancel, 391, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnCancel, 440, SpringLayout.WEST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnCancel, -12, SpringLayout.SOUTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnCancel, -32, SpringLayout.EAST, panel_2);
	btnCancel.setIcon(new ImageIcon("resources/CancelIcon.jpg"));
	btnCancel.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnCancel);
	btnCancel.addActionListener(this);
	
	JLabel lblR = new JLabel("PIN:");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnOK, 67, SpringLayout.SOUTH, lblR);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, lblR, -298, SpringLayout.SOUTH, panel_2);
	lblR.setFont(new Font("Cambria", Font.PLAIN, 70));
	panel_2.add(lblR);
	
	passwordField = new JPasswordField();
	sl_panel_2.putConstraint(SpringLayout.NORTH, passwordField, 112, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, passwordField, -77, SpringLayout.NORTH, btnOK);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnOK, -42, SpringLayout.EAST, passwordField);
	sl_panel_2.putConstraint(SpringLayout.EAST, lblR, -73, SpringLayout.WEST, passwordField);
	sl_panel_2.putConstraint(SpringLayout.WEST, passwordField, 318, SpringLayout.WEST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, passwordField, -194, SpringLayout.EAST, panel_2);
	passwordField.setFont(new Font("Calibri", Font.PLAIN, 54));
	panel_2.add(passwordField);
	passwordField.setColumns(10);
	
	JLabel lblWhatWouldYou = new JLabel("Re-enter the new PIN:");
	sl_panel_1.putConstraint(SpringLayout.NORTH, lblWhatWouldYou, 10, SpringLayout.NORTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.WEST, lblWhatWouldYou, 467, SpringLayout.WEST, panel_1);
	lblWhatWouldYou.setFont(new Font("Cambria", Font.PLAIN, 50));
	panel_1.add(lblWhatWouldYou);
	
	framePanel.add(this);
	framePanel.revalidate();
}
@Override
public void actionPerformed (ActionEvent ae)
{
	Object source = ae.getSource();
	
	if (source == btnOK)
	{
		if (pinChange.getNewPIN().equals(String.valueOf(passwordField.getPassword())))
		{
			if (ATMApplication.serverComm.sendPINChange(pinChange))
			{
				JOptionPane.showMessageDialog(null, "The PIN has been successfully changed", "PIN Changed", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "The PINs you entered do not match. Please try again.", "PINs do not match", JOptionPane.INFORMATION_MESSAGE);
			pinChange.setNewPIN("");
			new ChangePINNewPIN(framePanel, accountHolder, pinChange);
		}
	}
	
	
	
}

}
