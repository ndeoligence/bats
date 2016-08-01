package com.innotec.bats.client.atm.admin.view;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import java.awt.SystemColor;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


public class ATM_DNR_View extends JPanel
{
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ATM_DNR_View()
	{
		setBackground(SystemColor.inactiveCaption);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, SystemColor.inactiveCaptionBorder, SystemColor.activeCaption));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		panel.setBorder(new TitledBorder(new LineBorder(new Color(70, 130, 180), 2, true), "ATM Administrator", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(70, 130, 180)));
		panel.setBackground(SystemColor.inactiveCaption);
		add(panel, BorderLayout.CENTER);
		
		JLabel label = new JLabel("New City Bank");
		label.setFont(new Font("Cambria", Font.BOLD, 60));
		
		JLabel lblDispensedNotesRecord = new JLabel("Dispensed Notes Record");
		lblDispensedNotesRecord.setFont(new Font("Cambria", Font.BOLD, 44));
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Cambria", Font.PLAIN, 16));
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Cambria", Font.BOLD, 40));
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setFont(new Font("Cambria", Font.BOLD, 40));
		
		SpringLayout sl_panel = new SpringLayout();
		sl_panel.putConstraint(SpringLayout.NORTH, textPane, 22, SpringLayout.SOUTH, lblDispensedNotesRecord);
		sl_panel.putConstraint(SpringLayout.WEST, textPane, 397, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, textPane, -35, SpringLayout.NORTH, btnPrint);
		sl_panel.putConstraint(SpringLayout.EAST, textPane, 953, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnPrint, 342, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnPrint, -847, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnReturn, 342, SpringLayout.EAST, btnPrint);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnReturn, -24, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, btnPrint, -79, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnPrint, -24, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, label, -627, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, lblDispensedNotesRecord, 2, SpringLayout.SOUTH, label);
		sl_panel.putConstraint(SpringLayout.WEST, lblDispensedNotesRecord, 431, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.WEST, label, 476, SpringLayout.WEST, panel);
		panel.setLayout(sl_panel);
		panel.add(label);
		panel.add(lblDispensedNotesRecord);
		panel.add(textPane);
		panel.add(btnPrint);
		panel.add(btnReturn);
		
		table = new JTable();
		panel.add(table);

	}
}
