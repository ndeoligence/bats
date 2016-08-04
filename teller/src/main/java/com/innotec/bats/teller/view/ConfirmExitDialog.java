package com.innotec.bats.client.teller.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.SystemColor;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

import java.awt.Font;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

public class ConfirmExitDialog extends JDialog implements ActionListener
{

	private final JPanel contentPanel = new JPanel();
	private JButton okButton, cancelButton;
	
	public static void main(String[] args)
	{
		try
		{
			ConfirmExitDialog dialog = new ConfirmExitDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public ConfirmExitDialog()
	{
		setResizable(false);
		setBounds(100, 100, 447, 313);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaption);
		contentPanel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(new Color(70, 130, 180), 2)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			JLabel lblConfirmExit = new JLabel("Confirm Exit!");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblConfirmExit, 90, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblConfirmExit, 97, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, lblConfirmExit, -98, SpringLayout.EAST, contentPanel);
			lblConfirmExit.setHorizontalAlignment(SwingConstants.LEFT);
			lblConfirmExit.setFont(new Font("Cambria", Font.BOLD, 32));
			contentPanel.add(lblConfirmExit);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				okButton = new JButton("YES");
				okButton.setHorizontalTextPosition(SwingConstants.CENTER);
				okButton.setFont(new Font("Cambria", Font.PLAIN, 18));
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("NO");
				cancelButton.setFont(new Font("Cambria", Font.PLAIN, 18));
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
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
			System.exit(0);
		}
		if(source == cancelButton)
		{
			//this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			//this.removeAll();
			this.dispose();
		}
	}

}
