package com.innotec.bats.client.atm.accountholder.view;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import com.innotec.bats.client.atm.accountholder.control.ATMApplication;
import com.innotec.bats.client.atm.accountholder.model.ATMUserLogout;
import com.innotec.bats.client.atm.accountholder.model.CardValidation;
import com.innotec.bats.client.atm.control.ATM_ServerComm;
import com.innotec.bats.general.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class ATMUserLogin extends JPanel implements KeyListener
{
	private JTextField textField;
	private JPasswordField passwordField;
	private CardRetrieval cardRetrieval;
	private Card insertedCard, retrievedCard;
	private CardValidation cardValidation;
	private JPanel framePanel;
	private String pinEntered;
	private String accountHolderCardNo;
	JButton button;

/**
 * Create the panel.
 */
public ATMUserLogin (JPanel framePanel)
{
	framePanel.removeAll();
	this.framePanel = framePanel;
	
	pinEntered = "";
	
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
	
	button = new JButton("Cancel");
	sl_panel_1.putConstraint(SpringLayout.NORTH, button, 73, SpringLayout.SOUTH, passwordField);
	sl_panel_1.putConstraint(SpringLayout.WEST, button, 563, SpringLayout.WEST, panel_1);
	sl_panel_1.putConstraint(SpringLayout.SOUTH, button, -48, SpringLayout.SOUTH, panel_1);
	sl_panel_1.putConstraint(SpringLayout.EAST, button, 767, SpringLayout.WEST, panel_1);
	button.setFont(new Font("Cambria", Font.PLAIN, 38));
	button.setIcon(new ImageIcon("resources/CancelIcon.jpg"));
	panel_1.add(button);
	
	
	
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
			pinEntered = String.copyValueOf(passwordField.getPassword());
			System.out.println("pinEntered Variable: " + pinEntered);
			cardRetrieval = new CardRetrieval(textField.getText());
			insertedCard = new Card(textField.getText(), pinEntered, true);
			ATMApplication.serverComm.openConnection();
			retrievedCard = ATMApplication.serverComm.sendCardRetrieval(cardRetrieval);
			System.out.println("Retrieved Card from server: " +retrievedCard.getCardNo());
			cardValidation = new CardValidation(insertedCard, retrievedCard);
			
			if (cardValidation.validate())
			{
				System.out.println("Validate returned true");
				
				AccountHolderRetrievalByCardNo accountHolderRetrievalByCardNo = new AccountHolderRetrievalByCardNo(accountHolderCardNo);
				AccountHolder accountHolder = ATMApplication.serverComm.sendAccountHolderRetrievalByCardNo(accountHolderRetrievalByCardNo);
				System.out.println("AccountHolder retrieved.  " + accountHolder.toString());
				framePanel.removeAll();
				framePanel.revalidate();
				new ATMAccountHolderMainMenu(framePanel, accountHolder);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "The PIN you entered is incorrect. Please try again.", "Incorrect PIN", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Validate returned false");
				textField.setText("");
				passwordField.setText("");
			}
		}

	}
	
	if (source == button)
	{
		SessionTermination sessionTermination = new SessionTermination();
		new ATMUserLogout(sessionTermination);
		framePanel.removeAll();
		new ATMWelcomeScreen(framePanel);
		
	}
}

@Override
public void keyTyped (KeyEvent ke)
{
	
}


}
