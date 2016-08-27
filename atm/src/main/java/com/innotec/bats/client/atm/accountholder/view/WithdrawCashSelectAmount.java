package com.innotec.bats.client.atm.accountholder.view;

import javax.swing.*;

import java.awt.*;

import javax.swing.border.*;

public class WithdrawCashSelectAmount extends JPanel {
	private JPanel framePanel;

	public WithdrawCashSelectAmount(JPanel framePanel) {
		this.framePanel = framePanel;
		framePanel.removeAll();

		setBackground(SystemColor.inactiveCaption);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -628, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, this);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, SystemColor.windowBorder));
		panel.setBackground(SystemColor.inactiveCaption);
		add(panel);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		JLabel label = new JLabel("");
		sl_panel.putConstraint(SpringLayout.EAST, label, 1326, SpringLayout.WEST, panel);
		label.setBorder(BorderFactory.createEtchedBorder());
		sl_panel.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, panel);
		label.setIcon(new ImageIcon("resources/NewCityBankLogoSmall.jpg"));
		panel.add(label);

		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 6, SpringLayout.SOUTH, panel);
		panel_1.setBackground(SystemColor.inactiveCaption);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 1352, SpringLayout.WEST, this);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, SystemColor.windowBorder));
		add(panel_1);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);

		JPanel panel_2 = new JPanel();
		sl_panel_1.putConstraint(SpringLayout.NORTH, panel_2, 84, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, panel_2, 232, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, panel_2, -24, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, panel_2, -233, SpringLayout.EAST, panel_1);
		panel_2.setBackground(SystemColor.inactiveCaption);
		panel_2.setBorder(BorderFactory.createEtchedBorder());
		panel_1.add(panel_2);
		SpringLayout sl_panel_2 = new SpringLayout();
		panel_2.setLayout(sl_panel_2);

		JButton btnTransferMoney = new JButton("R200");
		sl_panel_2.putConstraint(SpringLayout.WEST, btnTransferMoney, 20, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnTransferMoney, -306, SpringLayout.SOUTH, panel_2);
		btnTransferMoney.setIcon(null);
		btnTransferMoney.setFont(new Font("Cambria", Font.PLAIN, 38));
		panel_2.add(btnTransferMoney);

		JButton btnHelp = new JButton("Help");
		sl_panel_2.putConstraint(SpringLayout.WEST, btnHelp, 20, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnHelp, 0, SpringLayout.EAST, btnTransferMoney);
		btnHelp.setIcon(new ImageIcon("resources/HelpIcon.jpg"));
		btnHelp.setFont(new Font("Cambria", Font.PLAIN, 38));
		panel_2.add(btnHelp);

		JButton btnCancel = new JButton("Cancel");
		sl_panel_2.putConstraint(SpringLayout.WEST, btnCancel, 21, SpringLayout.EAST, btnHelp);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnCancel, -32, SpringLayout.EAST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnHelp, 0, SpringLayout.SOUTH, btnCancel);
		sl_panel_2.putConstraint(SpringLayout.NORTH, btnHelp, 0, SpringLayout.NORTH, btnCancel);
		sl_panel_2.putConstraint(SpringLayout.NORTH, btnCancel, 384, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnCancel, -24, SpringLayout.SOUTH, panel_2);
		btnCancel.setIcon(new ImageIcon("resources/CancelIcon.jpg"));
		btnCancel.setFont(new Font("Cambria", Font.PLAIN, 38));
		panel_2.add(btnCancel);

		JButton btnR = new JButton("R100");
		sl_panel_2.putConstraint(SpringLayout.NORTH, btnTransferMoney, 6, SpringLayout.SOUTH, btnR);
		sl_panel_2.putConstraint(SpringLayout.NORTH, btnR, 10, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnR, -400, SpringLayout.SOUTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnTransferMoney, 0, SpringLayout.EAST, btnR);
		sl_panel_2.putConstraint(SpringLayout.WEST, btnR, 20, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnR, -452, SpringLayout.EAST, panel_2);
		btnR.setFont(new Font("Cambria", Font.PLAIN, 38));
		panel_2.add(btnR);

		JButton btnR_1 = new JButton("R300");
		sl_panel_2.putConstraint(SpringLayout.NORTH, btnR_1, 6, SpringLayout.SOUTH, btnTransferMoney);
		sl_panel_2.putConstraint(SpringLayout.WEST, btnR_1, 0, SpringLayout.WEST, btnTransferMoney);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnR_1, -212, SpringLayout.SOUTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnR_1, -452, SpringLayout.EAST, panel_2);
		btnR_1.setFont(new Font("Cambria", Font.PLAIN, 38));
		panel_2.add(btnR_1);

		JButton btnR_2 = new JButton("R500");
		sl_panel_2.putConstraint(SpringLayout.NORTH, btnR_2, -1, SpringLayout.NORTH, btnR);
		sl_panel_2.putConstraint(SpringLayout.WEST, btnR_2, 23, SpringLayout.EAST, btnR);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnR_2, -399, SpringLayout.SOUTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnR_2, -32, SpringLayout.EAST, panel_2);
		btnR_2.setFont(new Font("Cambria", Font.PLAIN, 38));
		panel_2.add(btnR_2);

		JButton btnOtherAmount = new JButton("Other amount");
		sl_panel_2.putConstraint(SpringLayout.WEST, btnOtherAmount, 23, SpringLayout.EAST, btnR_1);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnOtherAmount, -212, SpringLayout.SOUTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.NORTH, btnOtherAmount, 188, SpringLayout.NORTH, btnR);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnOtherAmount, -32, SpringLayout.EAST, panel_2);
		btnOtherAmount.setFont(new Font("Cambria", Font.PLAIN, 38));
		panel_2.add(btnOtherAmount);

		JButton button = new JButton("R1000");
		sl_panel_2.putConstraint(SpringLayout.NORTH, button, 0, SpringLayout.NORTH, btnTransferMoney);
		sl_panel_2.putConstraint(SpringLayout.WEST, button, 23, SpringLayout.EAST, btnTransferMoney);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, button, 0, SpringLayout.SOUTH, btnTransferMoney);
		sl_panel_2.putConstraint(SpringLayout.EAST, button, -32, SpringLayout.EAST, panel_2);
		button.setFont(new Font("Cambria", Font.PLAIN, 38));
		panel_2.add(button);

		JLabel lblWhatWouldYou = new JLabel("Select the amount you would like to withdraw:");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblWhatWouldYou, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblWhatWouldYou, 169, SpringLayout.WEST, panel_1);
		lblWhatWouldYou.setFont(new Font("Cambria", Font.PLAIN, 50));
		panel_1.add(lblWhatWouldYou);

		framePanel.add(this);
		framePanel.revalidate();
	}

}
