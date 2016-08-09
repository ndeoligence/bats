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
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ConfirmCloseAcc extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JLabel lblCloseAccount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			ConfirmCloseAcc dialog = new ConfirmCloseAcc();
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
	public ConfirmCloseAcc()
	{
		setResizable(false);
		setTitle("Confirm Closing Of Account");
		setBounds(100, 100, 448, 304);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaption);
		contentPanel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(new Color(70, 130, 180), 2)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			lblCloseAccount = new JLabel("Close Account:");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblCloseAccount, 55, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, lblCloseAccount, -228, SpringLayout.EAST, contentPanel);
			lblCloseAccount.setHorizontalAlignment(SwingConstants.LEFT);
			lblCloseAccount.setFont(new Font("Cambria", Font.BOLD, 22));
			contentPanel.add(lblCloseAccount);
		}
		{
			JLabel label = new JLabel("00000000000000");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, label, 57, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, label, 43, SpringLayout.EAST, lblCloseAccount);
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setFont(new Font("Cambria", Font.ITALIC, 18));
			contentPanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton btnYes = new JButton("YES");
				btnYes.setFont(new Font("Cambria", Font.BOLD, 18));
				buttonPane.add(btnYes);
			}
			{
				JButton btnNo = new JButton(" NO ");
				btnNo.setFont(new Font("Cambria", Font.BOLD, 18));
				buttonPane.add(btnNo);
			}
		}
	}

}
