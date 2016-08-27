package com.innotec.bats.client.atm.admin.view;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

import java.awt.BorderLayout;

import javax.swing.SpringLayout;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.innotec.bats.client.atm.accountholder.control.ATMApplication;
import com.innotec.bats.client.atm.admin.model.DNR_Manager;
import com.innotec.bats.general.BalanceSheetRequest;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ATM_AdminHome extends JPanel implements ActionListener {
	private JPanel framePanel;
	private JButton btnOpenDispenser, btnServerBalanceSheet, btnPrintDnr, btnLeave, btnViewDnr;
	private DNR_Manager dnr_Manager;
	private ATM_DNR_View atm_DNR_View;
	private BalanceSheetRequest balanceSheetRequest;

	/**
	 * Create the panel.
	 */
	public ATM_AdminHome(JPanel framePanel) {
		this.framePanel = framePanel;
		framePanel.removeAll();
		setBackground(SystemColor.inactiveCaption);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, SystemColor.inactiveCaptionBorder, SystemColor.activeCaption));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		panel.setBorder(new TitledBorder(new LineBorder(new Color(70, 130, 180), 2, true), "ATM Administrator",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(70, 130, 180)));
		panel.setBackground(SystemColor.inactiveCaption);
		add(panel, BorderLayout.CENTER);

		btnOpenDispenser = new JButton("Open\r\n\r\n Dispenser");
		btnOpenDispenser.setFont(new Font("Cambria", Font.BOLD, 45));

		btnServerBalanceSheet = new JButton("Balance Sheet");
		btnServerBalanceSheet.setFont(new Font("Cambria", Font.BOLD, 45));
		btnServerBalanceSheet.addActionListener(this);

		btnPrintDnr = new JButton("Print DNR");
		btnPrintDnr.setFont(new Font("Cambria", Font.BOLD, 45));
		btnPrintDnr.addActionListener(this);

		btnViewDnr = new JButton("View DNR");
		btnViewDnr.setFont(new Font("Cambria", Font.BOLD, 45));
		btnViewDnr.addActionListener(this);

		btnLeave = new JButton("Leave");
		btnLeave.setFont(new Font("Cambria", Font.BOLD, 45));
		btnLeave.addActionListener(this);

		SpringLayout sl_panel = new SpringLayout();
		sl_panel.putConstraint(SpringLayout.NORTH, btnViewDnr, 304, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnViewDnr, -91, SpringLayout.NORTH, btnLeave);
		sl_panel.putConstraint(SpringLayout.EAST, btnLeave, -495, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnLeave, -185, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnPrintDnr, -45, SpringLayout.NORTH, btnViewDnr);
		sl_panel.putConstraint(SpringLayout.NORTH, btnLeave, 91, SpringLayout.SOUTH, btnServerBalanceSheet);
		sl_panel.putConstraint(SpringLayout.WEST, btnViewDnr, 208, SpringLayout.EAST, btnServerBalanceSheet);
		sl_panel.putConstraint(SpringLayout.EAST, btnViewDnr, -209, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnOpenDispenser, -45, SpringLayout.NORTH, btnServerBalanceSheet);
		sl_panel.putConstraint(SpringLayout.EAST, btnServerBalanceSheet, -779, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnPrintDnr, -208, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnServerBalanceSheet, -348, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, btnServerBalanceSheet, 304, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnLeave, 493, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnServerBalanceSheet, 208, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnPrintDnr, 208, SpringLayout.EAST, btnOpenDispenser);
		sl_panel.putConstraint(SpringLayout.WEST, btnOpenDispenser, 208, SpringLayout.WEST, panel);
		panel.setLayout(sl_panel);

		JLabel lblNewCityBank = new JLabel("New City Bank");
		sl_panel.putConstraint(SpringLayout.NORTH, btnPrintDnr, 93, SpringLayout.SOUTH, lblNewCityBank);
		sl_panel.putConstraint(SpringLayout.NORTH, btnOpenDispenser, 93, SpringLayout.SOUTH, lblNewCityBank);
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewCityBank, 26, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewCityBank, 485, SpringLayout.WEST, panel);
		lblNewCityBank.setFont(new Font("Cambria", Font.BOLD, 57));
		panel.add(lblNewCityBank);
		panel.add(btnOpenDispenser);
		panel.add(btnPrintDnr);
		panel.add(btnServerBalanceSheet);
		panel.add(btnViewDnr);
		panel.add(btnLeave);

		framePanel.add(this);

	}

	@Override
	public void actionPerformed(ActionEvent acEvent) {
		Object source = acEvent.getSource();
		if (source == btnOpenDispenser) {
			dnr_Manager = new DNR_Manager();
			JOptionPane.showMessageDialog(null, "The Dispenser Has Been Opened", "DISPENSER OPEN!",
					JOptionPane.INFORMATION_MESSAGE);
		}
		if (source == btnServerBalanceSheet) {
			// balanceSheetRequest = new
			// BalanceSheetRequest(ATMApplication.serverComm.atmID, startDate,
			// endDate);
			ATMApplication.serverComm.sendBalanceSheetRequest(balanceSheetRequest);
		}
		if (source == btnViewDnr) {
			atm_DNR_View = new ATM_DNR_View(framePanel);
			this.revalidate();
			this.repaint();
		}
		if (source == btnPrintDnr) {
			dnr_Manager.printDNR();
			JOptionPane.showMessageDialog(null, "Dispensed Notes Record is being printed", "PRINTING DNR!",
					JOptionPane.INFORMATION_MESSAGE);
		}
		if (source == btnLeave) {
			ATMApplication.serverComm.closeConnection();
			System.exit(0);
		}
	}

}
