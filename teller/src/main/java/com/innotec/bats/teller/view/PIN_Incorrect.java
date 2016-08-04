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

import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PIN_Incorrect extends JDialog implements ActionListener
{

	private final JPanel contentPanel = new JPanel();
	private JLabel lblPinIncorrect;
	private JLabel lblPleaseRepeat;
	private JButton btnOk;

	public static void main(String[] args)
	{
		try
		{
			PIN_Incorrect dialog = new PIN_Incorrect();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public PIN_Incorrect()
	{
		setResizable(false);
		setTitle("ERROR!");
		setBounds(100, 100, 429, 328);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaption);
		contentPanel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(new Color(70, 130, 180), 2)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblPinIncorrect = new JLabel("PIN INCORRECT!");
			lblPinIncorrect.setHorizontalAlignment(SwingConstants.LEFT);
			lblPinIncorrect.setFont(new Font("Cambria", Font.BOLD, 26));
		}
		{
			lblPleaseRepeat = new JLabel("PLEASE REPEAT");
			lblPleaseRepeat.setHorizontalAlignment(SwingConstants.LEFT);
			lblPleaseRepeat.setFont(new Font("Cambria", Font.BOLD, 26));
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(102, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPinIncorrect)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(lblPleaseRepeat, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)))
					.addGap(88))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(83, Short.MAX_VALUE)
					.addComponent(lblPinIncorrect)
					.addGap(20)
					.addComponent(lblPleaseRepeat, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(70))
		);
		gl_contentPanel.linkSize(SwingConstants.VERTICAL, new Component[] {lblPinIncorrect, lblPleaseRepeat});
		gl_contentPanel.linkSize(SwingConstants.HORIZONTAL, new Component[] {lblPinIncorrect, lblPleaseRepeat});
		contentPanel.setLayout(gl_contentPanel);
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
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
	}

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
