package com.innotec.bats.client.atm.accountholder.view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.*;

import com.innotec.bats.general.AccountHolder;


public class HelpShowFile extends JPanel implements ActionListener
{
	private JPanel framePanel;
	private JButton button;
	private AccountHolder accountHolder;

public HelpShowFile (JPanel framePanel, ImageIcon imageIcon, AccountHolder accountHolder)
{
	this.framePanel = framePanel;
	framePanel.removeAll();
	
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
	sl_panel_1.putConstraint(SpringLayout.NORTH, panel_2, 8, SpringLayout.NORTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.WEST, panel_2, 369, SpringLayout.WEST, panel_1);
	sl_panel_1.putConstraint(SpringLayout.EAST, panel_2, -370, SpringLayout.EAST, panel_1);
	panel_2.setBackground(SystemColor.inactiveCaption);
	panel_2.setBorder(BorderFactory.createEtchedBorder());
	panel_1.add(panel_2);
	SpringLayout sl_panel_2 = new SpringLayout();
	panel_2.setLayout(sl_panel_2);
	
	JLabel lblWhatWouldYou = new JLabel("");
	sl_panel_1.putConstraint(SpringLayout.SOUTH, lblWhatWouldYou, 121, SpringLayout.SOUTH, panel_2);
	sl_panel_1.putConstraint(SpringLayout.EAST, lblWhatWouldYou, 0, SpringLayout.EAST, panel_2);
	lblWhatWouldYou.setIcon(imageIcon);
	sl_panel_1.putConstraint(SpringLayout.NORTH, lblWhatWouldYou, 27, SpringLayout.NORTH, panel_2);
	sl_panel_1.putConstraint(SpringLayout.WEST, lblWhatWouldYou, 71, SpringLayout.WEST, panel_2);
	panel_2.add(lblWhatWouldYou);
	lblWhatWouldYou.setFont(new Font("Cambria", Font.PLAIN, 56));
	
	button = new JButton("OK");
	sl_panel_1.putConstraint(SpringLayout.SOUTH, panel_2, -12, SpringLayout.NORTH, button);
	sl_panel_1.putConstraint(SpringLayout.NORTH, button, -89, SpringLayout.SOUTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.SOUTH, button, -23, SpringLayout.SOUTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.WEST, button, 494, SpringLayout.WEST, panel_1);
	sl_panel_1.putConstraint(SpringLayout.EAST, button, -492, SpringLayout.EAST, panel_1);
	button.setIcon(new ImageIcon("resources/YesIcon.jpg"));
	button.setFont(new Font("Cambria", Font.PLAIN, 38));
	panel_1.add(button);
	button.addActionListener(this);
	
	framePanel.add(this);
	framePanel.revalidate();
}

@Override
public void actionPerformed (ActionEvent ae)
{
	Object source = ae.getSource();
	
	if (source == button)
	{
		new HelpChooseTopic(framePanel, accountHolder);
	}
}
}
