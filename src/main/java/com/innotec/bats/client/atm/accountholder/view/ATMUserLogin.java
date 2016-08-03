package com.innotec.bats.client.atm.accountholder.view;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import com.innotec.bats.client.atm.accountholder.control.ATMApplication;
import com.innotec.bats.client.atm.accountholder.model.ATMUserLogout;
import com.innotec.bats.client.atm.accountholder.model.CardValidation;
import com.innotec.bats.client.atm.admin.view.ATM_AdminHome;
import com.innotec.bats.client.atm.control.ATM_ServerComm;
import com.innotec.bats.general.*;
import com.innotec.bats.general.Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class ATMUserLogin extends JPanel implements KeyListener, ActionListener
{
	private JTextField textField;
	private JPasswordField passwordField;
	private CardRetrieval cardRetrieval;
	private Card insertedCard, retrievedCard;
	private CardValidation cardValidation;
	private JPanel framePanel;
	private String pinEntered;
	private String accountHolderCardNo;
	private JButton btnCancel;
	private int incorrectPINCounter;

/**
 * Create the panel.
 */
public ATMUserLogin (JPanel framePanel)
{
	framePanel.removeAll();
	this.framePanel = framePanel;
	
	pinEntered = "";
	incorrectPINCounter = 0;
	
	setBackground(SystemColor.inactiveCaption);
	SpringLayout springLayout = new SpringLayout();
	setLayout(springLayout);
	
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
	label.setIcon(new ImageIcon("resources/NewCityBankLogoMed.jpg"));
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
	sl_panel_1.putConstraint(SpringLayout.NORTH, label_1, 32, SpringLayout.NORTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.WEST, label_1, 535, SpringLayout.WEST, panel_1);
	label_1.setFont(new Font("Cambria", Font.BOLD, 40));
	panel_1.add(label_1);
	
	textField = new JTextField();
	sl_panel_1.putConstraint(SpringLayout.NORTH, textField, 6, SpringLayout.SOUTH, label_1);
	sl_panel_1.putConstraint(SpringLayout.EAST, textField, -522, SpringLayout.EAST, panel_1);
	textField.setFont(new Font("Cambria", Font.PLAIN, 34));
	textField.setColumns(10);
	textField.setBorder(BorderFactory.createLoweredSoftBevelBorder());
	panel_1.add(textField);
	textField.setFocusable(true);
	textField.grabFocus();
	textField.requestFocus();
	textField.requestFocusInWindow();
	
	JLabel label_2 = new JLabel("Enter PIN:");
	sl_panel_1.putConstraint(SpringLayout.NORTH, label_2, 49, SpringLayout.SOUTH, textField);
	sl_panel_1.putConstraint(SpringLayout.WEST, label_2, 571, SpringLayout.WEST, panel_1);
	label_2.setFont(new Font("Cambria", Font.BOLD, 40));
	panel_1.add(label_2);
	
	passwordField = new JPasswordField();
	sl_panel_1.putConstraint(SpringLayout.NORTH, passwordField, 6, SpringLayout.SOUTH, label_2);
	sl_panel_1.putConstraint(SpringLayout.WEST, passwordField, 522, SpringLayout.WEST, panel_1);
	sl_panel_1.putConstraint(SpringLayout.EAST, passwordField, -522, SpringLayout.EAST, panel_1);
	passwordField.addKeyListener(this);
	passwordField.setFont(new Font("Cambria", Font.PLAIN, 34));
	passwordField.setBorder(BorderFactory.createLoweredSoftBevelBorder());
	panel_1.add(passwordField);
	
	btnCancel = new JButton("Cancel");
	sl_panel_1.putConstraint(SpringLayout.NORTH, btnCancel, 73, SpringLayout.SOUTH, passwordField);
	sl_panel_1.putConstraint(SpringLayout.WEST, btnCancel, 549, SpringLayout.WEST, panel_1);
	sl_panel_1.putConstraint(SpringLayout.SOUTH, btnCancel, -48, SpringLayout.SOUTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.EAST, btnCancel, -14, SpringLayout.EAST, label_1);
	btnCancel.setFont(new Font("Cambria", Font.PLAIN, 38));
	btnCancel.setIcon(new ImageIcon("resources/CancelIcon.jpg"));
	panel_1.add(btnCancel);
	btnCancel.addActionListener(this);
	
	
	framePanel.add(this);
	framePanel.validate();
	framePanel.repaint();
	
}

@Override
public void keyPressed (KeyEvent ke)
{
	
}

@Override
public void keyReleased (KeyEvent ke)
{
	Object source = ke.getSource();
	
	if (source == passwordField)
	{
		if (String.valueOf(passwordField.getPassword()).length() == 4)
		{
			ATMApplication.serverComm.openConnection();
			
			pinEntered = String.copyValueOf(passwordField.getPassword());
			System.out.println("pinEntered Variable: " + pinEntered);
			cardRetrieval = new CardRetrieval(textField.getText());
			if (cardRetrieval.getCardNo().length() == 16)
			{
				insertedCard = new AccountHolderCard(textField.getText(), pinEntered, true, "");
			}
			else if (cardRetrieval.getCardNo().length() == 8)
				{
				insertedCard = new AdminCard(textField.getText(), pinEntered, true, "");
				}
					else
					{
						JOptionPane.showMessageDialog(null, "Invalid card number length. Please try again.", "Invalid Card Number", JOptionPane.INFORMATION_MESSAGE);
	
					}
			
			retrievedCard = ATMApplication.serverComm.sendCardRetrieval(cardRetrieval);
			System.out.println("Retrieved card from server: " +retrievedCard.getCardNo());
			cardValidation = new CardValidation(insertedCard, retrievedCard);
			
			if (cardValidation.validate()) //Correct PIN
			{
				System.out.println("Validate returned true");
				
				if (!(retrievedCard.isActive())) //Card deactivated
				{
					JOptionPane.showMessageDialog(null, "Your card has been deactivated due to incorrect PIN entries. Please visit your nearest branch to rectify.", "Card Blocked", JOptionPane.INFORMATION_MESSAGE);
					new ATMUserLogin(framePanel);
				}
				
				//PIN correct and card active
				if (insertedCard instanceof AccountHolderCard)
				{
					AccountHolderRetrievalByCardNo accountHolderRetrievalByCardNo = new AccountHolderRetrievalByCardNo(accountHolderCardNo);
					AccountHolder accountHolder = ATMApplication.serverComm.sendAccountHolderRetrievalByCardNo(accountHolderRetrievalByCardNo);
					System.out.println("AccountHolder retrieved.  " + accountHolder.toString());
					new ATMAccountHolderMainMenu(framePanel, accountHolder);
				}
				else
				{
					new ATM_AdminHome();
				}
			}
			else
			{
				incorrectPINCounter++;
				if (incorrectPINCounter < 3)
				{
					JOptionPane.showMessageDialog(null, "The PIN you entered is incorrect. You have " + (3 - incorrectPINCounter) + " tries left. Please try again.", "Incorrect PIN", JOptionPane.INFORMATION_MESSAGE);
				}
				
				if (incorrectPINCounter == 3)
				{
					if (ATMApplication.serverComm.sendCardDeactivation(new CardDeactivation(insertedCard.getCardNo(), ATMApplication.serverComm.getAtmId())))
					{
						JOptionPane.showMessageDialog(null, "You have entered your PIN incorrectly 3 times. Your card has been blocked. Please visit your nearest branch to unblock.", "Card Blocked", JOptionPane.INFORMATION_MESSAGE);
						new ATMWelcomeScreen(framePanel);
					}
				}
				System.out.println("Validate returned false");
				//textField.setText("");
				passwordField.setText("");
			}
		}

	}
}

@Override
public void keyTyped (KeyEvent ke)
{
	
}

@Override
public void actionPerformed (ActionEvent ke)
{
	Object source = ke.getSource();
	
	if (source == btnCancel)
	{
		SessionTermination sessionTermination = new SessionTermination();
		new ATMUserLogout(sessionTermination);
		framePanel.removeAll();
		new ATMWelcomeScreen(framePanel);
		
	}
}

}
