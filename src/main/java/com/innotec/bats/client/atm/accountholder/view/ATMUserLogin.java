package com.innotec.bats.client.atm.accountholder.view;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import com.innotec.bats.client.atm.accountholder.control.ATMApplication;
import com.innotec.bats.client.atm.accountholder.model.CardValidation;
import com.innotec.bats.client.atm.control.ATM_ServerComm;
import com.innotec.bats.general.Card;
import com.innotec.bats.general.CardRetrieval;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class ATMUserLogin extends JPanel implements KeyListener
{
	private JTextField textField;
	private JPasswordField passwordField;
	private int pinDigitCounter;
	private CardRetrieval cardRetrieval;
	private Card insertedCard, retrievedCard;
	private CardValidation cardValidation;
	private JPanel framePanel;

/**
 * Create the panel.
 */
public ATMUserLogin (JPanel framePanel)
{
	framePanel.removeAll();
	this.framePanel = framePanel;
	
	setBackground(SystemColor.inactiveCaption);
	SpringLayout springLayout = new SpringLayout();
	setLayout(springLayout);
	
	pinDigitCounter = 0;
	
	JPanel panel = new JPanel();
	springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, this);
	springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, this);
	springLayout.putConstraint(SpringLayout.SOUTH, panel, 246, SpringLayout.NORTH, this);
	springLayout.putConstraint(SpringLayout.EAST, panel, 1344, SpringLayout.WEST, this);
	panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, SystemColor.windowBorder));
	panel.setBackground(SystemColor.inactiveCaption);
	add(panel);
	this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
	SpringLayout sl_panel = new SpringLayout();
	panel.setLayout(sl_panel);
	
	JLabel label = new JLabel("");
	sl_panel.putConstraint(SpringLayout.EAST, label, 1318, SpringLayout.WEST, panel);
	label.setBorder(BorderFactory.createEtchedBorder());
	sl_panel.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.NORTH, panel);
	sl_panel.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, panel);
	label.setIcon(new ImageIcon("C:\\Users\\ilana\\workspace\\BatsGUIs\\resources\\NewCityBankLogoMed.jpg"));
	panel.add(label);
	
	JPanel panel_1 = new JPanel();
	panel_1.setBackground(SystemColor.inactiveCaption);
	springLayout.putConstraint(SpringLayout.NORTH, panel_1, 6, SpringLayout.SOUTH, panel);
	springLayout.putConstraint(SpringLayout.WEST, panel_1, 10, SpringLayout.WEST, this);
	springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -10, SpringLayout.SOUTH, this);
	springLayout.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, panel);
	panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, SystemColor.windowBorder));
	add(panel_1);
	SpringLayout sl_panel_1 = new SpringLayout();
	panel_1.setLayout(sl_panel_1);
	
	JLabel label_1 = new JLabel("Enter card no:");
	sl_panel_1.putConstraint(SpringLayout.NORTH, label_1, 52, SpringLayout.NORTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.WEST, label_1, 537, SpringLayout.WEST, panel_1);
	label_1.setFont(new Font("Cambria", Font.BOLD, 40));
	panel_1.add(label_1);
	
	textField = new JTextField();
	sl_panel_1.putConstraint(SpringLayout.NORTH, textField, 20, SpringLayout.SOUTH, label_1);
	sl_panel_1.putConstraint(SpringLayout.WEST, textField, 524, SpringLayout.WEST, panel_1);
	textField.setFont(new Font("Cambria", Font.PLAIN, 34));
	textField.setColumns(10);
	textField.setBorder(BorderFactory.createLoweredSoftBevelBorder());
	panel_1.add(textField);
	
	JLabel label_2 = new JLabel("Enter PIN:");
	sl_panel_1.putConstraint(SpringLayout.NORTH, label_2, 52, SpringLayout.SOUTH, textField);
	sl_panel_1.putConstraint(SpringLayout.WEST, label_2, 573, SpringLayout.WEST, panel_1);
	label_2.setFont(new Font("Cambria", Font.BOLD, 40));
	panel_1.add(label_2);
	
	passwordField = new JPasswordField();
	passwordField.addKeyListener(this);
	sl_panel_1.putConstraint(SpringLayout.NORTH, passwordField, 18, SpringLayout.SOUTH, label_2);
	sl_panel_1.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, textField);
	sl_panel_1.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, textField);
	passwordField.setFont(new Font("Cambria", Font.PLAIN, 34));
	passwordField.setBorder(BorderFactory.createLoweredSoftBevelBorder());
	panel_1.add(passwordField);
	
	framePanel.add(this);
	framePanel.validate();
	framePanel.repaint();
	
}

@Override
public void keyPressed (KeyEvent arg0)
{
}

@Override
public void keyReleased (KeyEvent arg0)
{
}

@Override
public void keyTyped (KeyEvent ke)
{
	Object source = ke.getSource();
	if (source == passwordField)
	{
		if (pinDigitCounter < 3)
		{
			pinDigitCounter++;
		}
		else
		{
			cardRetrieval = new CardRetrieval(textField.getText());
			insertedCard = new Card(textField.getText(), String.copyValueOf(passwordField.getPassword()), true);
			ATMApplication.serverComm.openConnection();
			retrievedCard = ATMApplication.serverComm.sendCardRetrieval(cardRetrieval);
			cardValidation = new CardValidation(insertedCard, retrievedCard);
			
			if (cardValidation.validate())
			{
				
				ATMAccountHolderMainMenu accountHolderMain = new ATMAccountHolderMainMenu(framePanel);
			}
		}
	}
}


}
