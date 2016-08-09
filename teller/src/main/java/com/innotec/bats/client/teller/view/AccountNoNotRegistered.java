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
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class AccountNoNotRegistered extends JDialog implements ActionListener 
{

	private final JPanel contentPanel = new JPanel();
	private JPanel framePanel;
	private JButton btnOk;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args)
//	{
//		try
//		{
//			MissingInformation dialog = new MissingInformation();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AccountNoNotRegistered()
	{
		//framePanel.removeAll();
		//this.framePanel = framePanel;
		setResizable(false);
		setTitle("Incorrect Information!");
		setBounds(100, 100, 488, 320);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaption);
		contentPanel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(new Color(70, 130, 180), 2)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			JLabel lblSomeInformationIs = new JLabel("Account Number Non-Existent!");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblSomeInformationIs, 99, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblSomeInformationIs, 85, SpringLayout.WEST, contentPanel);
			lblSomeInformationIs.setHorizontalAlignment(SwingConstants.LEFT);
			lblSomeInformationIs.setFont(new Font("Cambria", Font.BOLD, 22));
			contentPanel.add(lblSomeInformationIs);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				btnOk = new JButton("OK");
				btnOk.setFont(new Font("Cambria", Font.BOLD, 18));
				btnOk.addActionListener(this);
				buttonPane.add(btnOk);
			}
		}
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
//When ok is pressed we should return to the previous screen where 'x' icons will indicate missing or incorrect text.

	@Override
	public void actionPerformed(ActionEvent acEvent)
	{
		Object source = acEvent.getSource();
		if(source == btnOk)
		{
			this.dispose();
		}
	}
}
