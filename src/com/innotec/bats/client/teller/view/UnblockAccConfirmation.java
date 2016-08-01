
package com.innotec.bats.client.teller.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class UnblockAccConfirmation extends JDialog implements ActionListener
{

	private final JPanel contentPanel = new JPanel();
	private JLabel lblUnblockAccount;
	private JButton okButton;
	private boolean tf = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			UnblockAccConfirmation dialog = new UnblockAccConfirmation();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UnblockAccConfirmation()
	{
		setTitle("Unblock Account");
		setBounds(100, 100, 514, 356);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaption);
		contentPanel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(new Color(70, 130, 180), 2)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			lblUnblockAccount = new JLabel("Unblock Account:");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblUnblockAccount, 115, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, lblUnblockAccount, -234, SpringLayout.EAST, contentPanel);
			lblUnblockAccount.setHorizontalAlignment(SwingConstants.LEFT);
			lblUnblockAccount.setFont(new Font("Cambria", Font.BOLD, 27));
			contentPanel.add(lblUnblockAccount);
		}
		{
			JLabel label = new JLabel("00000000000000");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, label, 118, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, label, 33, SpringLayout.EAST, lblUnblockAccount);
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setFont(new Font("Cambria", Font.ITALIC, 22));
			contentPanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				okButton = new JButton("OK");
				okButton.setFont(new Font("Cambria", Font.BOLD, 18));
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent acEvent)
	{
		Object source = acEvent.getSource();
		if(source == okButton)
		{
			tf = true;
			this.dispose();
		}
	}
	
	public boolean okBtnPressed()
	{
		return tf;
	}

}
