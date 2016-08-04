package com.innotec.bats.client.atm.accountholder.view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.border.*;

import com.innotec.bats.general.AccountHolder;

public class ViewShowAccountBalance extends JPanel implements ActionListener
{
	private JPanel framePanel;
	private double accountBalance;
	private JButton btnOK, btnHelp;
	private AccountHolder accountHolder;

public ViewShowAccountBalance (JPanel framePanel, AccountHolder accountHolder, double accountBalance)
{
	this.framePanel = framePanel;
	framePanel.removeAll();
	
	this.accountBalance = accountBalance;
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
	
	btnHelp = new JButton("Help");
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnHelp, 393, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.WEST, btnHelp, 22, SpringLayout.WEST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnHelp, -10, SpringLayout.SOUTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnHelp, -450, SpringLayout.EAST, panel_2);
	btnHelp.setIcon(new ImageIcon("resources/HelpIcon.jpg"));
	btnHelp.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnHelp);
	btnHelp.addActionListener(this);
	
	btnOK = new JButton("OK");
	sl_panel_2.putConstraint(SpringLayout.WEST, btnOK, 242, SpringLayout.WEST, panel_2);
	sl_panel_2.putConstraint(SpringLayout.SOUTH, btnOK, -41, SpringLayout.NORTH, btnHelp);
	btnOK.setIcon(new ImageIcon("resources/YesIcon.jpg"));
	btnOK.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_2.add(btnOK);
	btnOK.addActionListener(this);
	
	JLabel lblR = new JLabel("R " + accountBalance);
	sl_panel_2.putConstraint(SpringLayout.NORTH, btnOK, 94, SpringLayout.SOUTH, lblR);
	sl_panel_2.putConstraint(SpringLayout.EAST, btnOK, 9, SpringLayout.EAST, lblR);
	sl_panel_2.putConstraint(SpringLayout.NORTH, lblR, 88, SpringLayout.NORTH, panel_2);
	sl_panel_2.putConstraint(SpringLayout.WEST, lblR, 251, SpringLayout.WEST, panel_2);
	lblR.setFont(new Font("Cambria", Font.PLAIN, 70));
	panel_2.add(lblR);
	
	Date date = new Date();
	
	JLabel lblWhatWouldYou = new JLabel("Account balance as at " + date);
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
	
	if (source == btnOK)
	{
		new ATMAccountHolderMainMenu(framePanel, accountHolder);
	}
	
	if (source == btnHelp)
	{
		new HelpShowFile(framePanel, new ImageIcon("resources/Help File View Balance.jpg"), accountHolder);
	}
}


}