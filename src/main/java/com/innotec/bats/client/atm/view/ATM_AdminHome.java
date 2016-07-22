package ATM_Administrator;
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

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ATM_AdminHome extends JPanel
{

	/**
	 * Create the panel.
	 */
	public ATM_AdminHome()
	{
		setBackground(SystemColor.inactiveCaption);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, SystemColor.inactiveCaptionBorder, SystemColor.activeCaption));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		panel.setBorder(new TitledBorder(new LineBorder(new Color(70, 130, 180), 2, true), "ATM Administrator", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(70, 130, 180)));
		panel.setBackground(SystemColor.inactiveCaption);
		add(panel, BorderLayout.CENTER);
		
		JButton btnOpenDispenser = new JButton("Open\r\n\r\n Dispenser");
		btnOpenDispenser.setForeground(new Color(70, 130, 180));
		btnOpenDispenser.setFont(new Font("Cambria", Font.BOLD, 45));
		btnOpenDispenser.setBackground(new Color(70, 130, 180));
		
		JButton btnServerBalanceSheet = new JButton("Balance Sheet");
		btnServerBalanceSheet.setForeground(new Color(70, 130, 180));
		btnServerBalanceSheet.setFont(new Font("Cambria", Font.BOLD, 45));
		btnServerBalanceSheet.setBackground(new Color(70, 130, 180));
		
		JButton btnPrintDnr = new JButton("Print DNR");
	
		btnPrintDnr.setForeground(new Color(70, 130, 180));
		btnPrintDnr.setFont(new Font("Cambria", Font.BOLD, 45));
		btnPrintDnr.setBackground(new Color(70, 130, 180));
		
		JButton btnViewDnr = new JButton("View DNR");
		btnViewDnr.setForeground(new Color(70, 130, 180));
		btnViewDnr.setFont(new Font("Cambria", Font.BOLD, 45));
		btnViewDnr.setBackground(new Color(70, 130, 180));
		
		JButton btnLeave = new JButton("Leave");
		btnLeave.setForeground(new Color(70, 130, 180));
		btnLeave.setFont(new Font("Cambria", Font.BOLD, 45));
		btnLeave.setBackground(new Color(70, 130, 180));
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

	}

}
