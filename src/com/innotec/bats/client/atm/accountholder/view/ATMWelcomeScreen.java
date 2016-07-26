package com.innotec.bats.client.atm.accountholder.view;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.border.EtchedBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import com.innotec.bats.client.atm.accountholder.control.ATMApplication;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ATMWelcomeScreen extends JPanel implements ActionListener
{

private JButton button;
private JPanel Panel, framePanel;
/**
 * Create the panel.
 */
public ATMWelcomeScreen (JPanel framePanel)
{
	this.framePanel = framePanel;
	
	setBackground(SystemColor.inactiveCaption);
	SpringLayout springLayout = new SpringLayout();
	setLayout(springLayout);
	
	
	JPanel panel = new JPanel();
	springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, this);
	springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, this);
	springLayout.putConstraint(SpringLayout.SOUTH, panel, 334, SpringLayout.NORTH, this);
	springLayout.putConstraint(SpringLayout.EAST, panel, 1344, SpringLayout.WEST, this);
	panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, SystemColor.windowBorder));
	panel.setBackground(SystemColor.inactiveCaption);
	add(panel);
	this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
	SpringLayout sl_panel = new SpringLayout();
	panel.setLayout(sl_panel);
	
	JLabel label = new JLabel("");
	sl_panel.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.NORTH, panel);
	sl_panel.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, panel);
	sl_panel.putConstraint(SpringLayout.EAST, label, -10, SpringLayout.EAST, panel);
	label.setIcon(new ImageIcon("resources/NewCityBankLogo.jpg"));
	label.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	panel.add(label);
	
	JPanel panel_1 = new JPanel();
	panel_1.setBackground(SystemColor.inactiveCaption);
	springLayout.putConstraint(SpringLayout.NORTH, panel_1, 6, SpringLayout.SOUTH, panel);
	springLayout.putConstraint(SpringLayout.WEST, panel_1, 10, SpringLayout.WEST, this);
	springLayout.putConstraint(SpringLayout.SOUTH, panel_1, 397, SpringLayout.SOUTH, panel);
	springLayout.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, panel);
	panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, SystemColor.windowBorder));
	add(panel_1);
	SpringLayout sl_panel_1 = new SpringLayout();
	panel_1.setLayout(sl_panel_1);
	
	button = new JButton("");
	sl_panel_1.putConstraint(SpringLayout.WEST, button, 554, SpringLayout.WEST, panel_1);
	sl_panel_1.putConstraint(SpringLayout.SOUTH, button, -67, SpringLayout.SOUTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.EAST, button, -554, SpringLayout.EAST, panel_1);
	button.addActionListener(this);
	button.setIcon(new ImageIcon("resources/BATSButton.jpg"));
	button.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	button.setBackground(Color.WHITE);
	panel_1.add(button);
	
	JLabel label_1 = new JLabel("Welcome");
	sl_panel_1.putConstraint(SpringLayout.NORTH, button, 24, SpringLayout.SOUTH, label_1);
	sl_panel_1.putConstraint(SpringLayout.NORTH, label_1, 10, SpringLayout.NORTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.WEST, label_1, 526, SpringLayout.WEST, panel_1);
	label_1.setFont(new Font("Cambria", Font.PLAIN, 70));
	panel_1.add(label_1);
	
	framePanel.add(this);
	framePanel.revalidate();
}

@Override
public void actionPerformed (ActionEvent e)
{
	Object source = e.getSource();
	
	if (source == button)
	{
		ATMUserLogin atmUserLogin = new ATMUserLogin(framePanel);

	}
}
}
